package my.firstaapp.myeduskuntaapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ParlamentData::class], version = 1, exportSchema = false)
abstract class ParlamentDatabase: RoomDatabase() {

    abstract val parlamentDatabaseDao: ParlamentDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: ParlamentDatabase? = null

        fun getInstance(context: Context): ParlamentDatabase {
            // Multiple threads can ask for the database at the same time, ensure we only initialize
            // it once by using synchronized. Only one thread may enter a synchronized block at a
            // time.
            synchronized(this) {

                var instance = INSTANCE

                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ParlamentDatabase::class.java,
                        "parliament_member_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    // Assign INSTANCE to the newly created database.
                    INSTANCE = instance
                }

                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }
}