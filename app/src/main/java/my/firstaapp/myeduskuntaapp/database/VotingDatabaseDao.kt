package my.firstaapp.myeduskuntaapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface VotingDatabaseDao {

    @Insert
    fun insert(voting: VotingInfo) // inserts the votingInfo

    @Insert(onConflict = OnConflictStrategy.REPLACE)  // updates the database
    suspend fun insertOrUpdate(voting: VotingInfo)

    @Query("SELECT * FROM voting_info_table")
    fun getAllInfo(): LiveData<List<VotingInfo>>
}