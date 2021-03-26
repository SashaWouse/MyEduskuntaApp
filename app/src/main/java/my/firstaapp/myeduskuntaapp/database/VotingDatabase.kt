package my.firstaapp.myeduskuntaapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = [VotingInfo::class], version = 1, exportSchema = false)
abstract class VotingDatabase: RoomDatabase() {

    abstract val votingDatabaseDao: VotingDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: VotingDatabase? = null

        fun getInstance(context: Context): VotingDatabase {

            synchronized(this){
                var instance = INSTANCE

                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext, //MyApp.appContext,
                        VotingDatabase::class.java,
                        "vote_history_database"
                    )
                        .fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}