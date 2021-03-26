package my.firstaapp.myeduskuntaapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ParlamentDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(parlament: ParlamentData)

    @Query("SELECT * FROM parlament_members_table ORDER BY surname")
    fun getAllMembers(): LiveData<List<ParlamentData>>
}
