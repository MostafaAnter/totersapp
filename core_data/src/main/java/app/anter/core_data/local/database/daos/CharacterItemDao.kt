package app.anter.core_data.local.database.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.anter.core_data.remote.responses.charactersResponse.HeroObject

/**
 * Created by Mostafa Anter on 6/30/21.
 */
@Dao
interface CharacterItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<HeroObject>)

    @Query("SELECT * FROM HeroObject")
    fun pagingAllDataSource(): PagingSource<Int, HeroObject>

    @Query("SELECT * FROM HeroObject WHERE id LIKE :id")
    suspend fun findItemByID(id: Int): HeroObject

    @Query("DELETE FROM HeroObject")
    suspend fun clearAll()
}