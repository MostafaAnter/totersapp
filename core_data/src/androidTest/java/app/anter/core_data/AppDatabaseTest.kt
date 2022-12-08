package app.anter.core_data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import app.anter.core_data.local.database.AppDatabase
import app.anter.core_data.local.database.daos.CharacterItemDao
import app.anter.core_data.remote.responses.charactersResponse.HeroObject
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

/**
 * Created by Mostafa Anter on 6/30/21.
 */
@ExperimentalCoroutinesApi
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4::class)
@SmallTest
class AppDatabaseTest{
    private lateinit var database: AppDatabase
    private lateinit var characterItemDao: CharacterItemDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java,
        ).allowMainThreadQueries().build()
        // init galleryImagesDao
        characterItemDao = database.characterItemDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun stage1_insertGamesToDatabase() = runBlockingTest {

        // fake data to database
        val listOfCharacters = listOf(
            HeroObject(
                id = 1,
                name = null,
                description = null,
                modified = null,
                thumbnail = null,
                resourceURI = null
            ),
            HeroObject(
                id = 2,
                name = null,
                description = null,
                modified = null,
                thumbnail = null,
                resourceURI = null
            ),
            HeroObject(
                id = 3,
                name = null,
                description = null,
                modified = null,
                thumbnail = null,
                resourceURI = null
            )

        )
        // add list of images to database
        characterItemDao.insertAll(listOfCharacters)

        // prepare pager to retrieve data
        val flowPagingData =  Pager(
            PagingConfig(
                pageSize = 20,
                enablePlaceholders = true
            )
        ) {
            characterItemDao.pagingAllDataSource()
        }.flow

        val differ = AsyncPagingDataDiffer(
            diffCallback = MyDiffCallback(),
            updateCallback = NoopListCallback(),
            workerDispatcher = Dispatchers.Main
        )

        val job = launch {
            flowPagingData.collectLatest {
                differ.submitData(it)
            }
        }

        // Wait for transforms and the differ to process all updates.
        advanceUntilIdle()

        assertThat(listOfCharacters.get(0).id).isEqualTo(differ.snapshot().items.get(0).id)
        assertThat(listOfCharacters.get(1).id).isEqualTo(differ.snapshot().items.get(1).id)
        assertThat(listOfCharacters.get(2).id).isEqualTo(differ.snapshot().items.get(2).id)
        assertThat(listOfCharacters.size).isEqualTo(differ.snapshot().items.size)

        // its impostant to end jobb
        job.cancel()
    }

    @Test
    fun stage2_deleteAllGamesFromDatabase() = runBlockingTest {
        characterItemDao.clearAll()


        // prepare pager to retrieve data
        val flowPagingData =  Pager(
            PagingConfig(
                pageSize = 20,
                enablePlaceholders = true
            )
        ) {
            characterItemDao.pagingAllDataSource()
        }.flow

        val differ = AsyncPagingDataDiffer(
            diffCallback = MyDiffCallback(),
            updateCallback = NoopListCallback(),
            workerDispatcher = Dispatchers.Main
        )

        val job = launch {
            flowPagingData.collectLatest {
                differ.submitData(it)
            }
        }

        // Wait for transforms and the differ to process all updates.
        advanceUntilIdle()
        assertThat(differ.snapshot().items.size).isEqualTo(0)

        job.cancel()
    }

    // helper class for test
    class NoopListCallback : ListUpdateCallback {
        override fun onChanged(position: Int, count: Int, payload: Any?) {}
        override fun onMoved(fromPosition: Int, toPosition: Int) {}
        override fun onInserted(position: Int, count: Int) {}
        override fun onRemoved(position: Int, count: Int) {}
    }

    class MyDiffCallback : DiffUtil.ItemCallback<HeroObject>() {
        override fun areItemsTheSame(oldItem: HeroObject, newItem: HeroObject): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: HeroObject, newItem: HeroObject): Boolean {
            return oldItem == newItem
        }
    }
}