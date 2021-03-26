package my.firstaapp.myeduskuntaapp.repositories

import androidx.lifecycle.LiveData
import my.firstaapp.myeduskuntaapp.ParlamentApp
import my.firstaapp.myeduskuntaapp.database.VotingDatabase
import my.firstaapp.myeduskuntaapp.database.VotingInfo
import my.firstaapp.myeduskuntaapp.internet.VotingApi

object VotingRepository{
    private val dao = VotingDatabase.getInstance(ParlamentApp.appContext).votingDatabaseDao
    val votingData: LiveData<List<VotingInfo>> = dao.getAllInfo()

    suspend fun refreshVotingDataEntry() {
        val votes = VotingApi.retrofitService.getAllVotingRecords()
        votes.forEach { dao.insertOrUpdate(it) }
    }

    suspend fun voteLikeData(id: Int){
        VotingApi.retrofitService.voteLike(id.toString())
    }

    suspend fun voteDislikeData(id: Int){
        VotingApi.retrofitService.voteDislike(id.toString())
    }
}