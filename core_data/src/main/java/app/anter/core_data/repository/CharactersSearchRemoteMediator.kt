package app.anter.core_data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import app.anter.core.util.NetworkHelper
import app.anter.core_data.local.database.daos.CharacterItemDao
import app.anter.core_data.remote.responses.charactersResponse.HeroObject
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by Mostafa Anter on 4/1/21.
 */
@OptIn(ExperimentalPagingApi::class)
class CharactersSearchRemoteMediator(
    private val query: String?,
    private val mainRepository: AppRepository,
    private val networkHelper: NetworkHelper,
    private val characterItemDao: CharacterItemDao

) : RemoteMediator<Int, HeroObject>() {

    // prevent getting data from server
//    override suspend fun initialize(): InitializeAction {
//        val cacheTimeout = TimeUnit.HOURS.convert(1, TimeUnit.MILLISECONDS)
//        return if (System.currentTimeMillis() - db.lastUpdated() >= cacheTimeout)
//        {
//            // Cached data is up-to-date, so there is no need to re-fetch
//            // from the network.
//            InitializeAction.SKIP_INITIAL_REFRESH
//        } else {
//            // Need to refresh cached data from network; returning
//            // LAUNCH_INITIAL_REFRESH here will also block RemoteMediator's
//            // APPEND and PREPEND from running until REFRESH succeeds.
//            InitializeAction.LAUNCH_INITIAL_REFRESH
//        }
//    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, HeroObject>
    ): MediatorResult {
        return try {
            // because i'll load all data once
            when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()

                    // You must explicitly check if the last item is null when
                    // appending, since passing null to networkService is only
                    // valid for initial load. If lastItem is null it means no
                    // items were loaded after the initial REFRESH and there are
                    // no more items to load.
                    if (lastItem == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }

                    lastItem.id
                }

            }

            if (networkHelper.isNetworkConnected()) {

                coroutineScope {
                    try {
                        val charactersDeferred =
                            async { mainRepository.searchCharacters(query) }

                        val characters = charactersDeferred.await()

                        //remove old data from database
                        if (loadType == LoadType.REFRESH) {
                            characterItemDao.clearAll()
                        }

                        //update database
                        characterItemDao.insertAll(characters.body()?.data?.results ?: listOf())

                        // always true because get all data once
                        MediatorResult.Success(endOfPaginationReached = true)
                    } catch (e: Exception) {
                        MediatorResult.Error(Throwable(e))
                    }
                }


            } else {
                MediatorResult.Error(Throwable("No internet connection"))
            }

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }


}