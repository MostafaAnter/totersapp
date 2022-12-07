package app.anter.core_data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import app.anter.core_data.local.database.daos.CharacterItemDao
import app.anter.core_data.remote.responses.charactersResponse.HeroObject

/**
 * Created by Mostafa Anter on 3/17/21.
 */

@Database(
    entities = [
        HeroObject::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterItemDao(): CharacterItemDao

    companion object {
        private const val DATABASE_NAME: String = "app_database"

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            /** this call back will help if we want
                             * to insert data to database after it created at first time
                             * see this article for more detail
                             * https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
                             **/
                        }
                    }
                )
                .build()
        }
    }


}