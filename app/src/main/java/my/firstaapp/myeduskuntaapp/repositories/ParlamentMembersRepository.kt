package my.firstaapp.myeduskuntaapp.repositories

import androidx.lifecycle.LiveData
import my.firstaapp.myeduskuntaapp.ParlamentApp
import my.firstaapp.myeduskuntaapp.database.ParlamentData
import my.firstaapp.myeduskuntaapp.database.ParlamentDatabase
import my.firstaapp.myeduskuntaapp.internet.ParlamentApi
import timber.log.Timber

object ParlamentMembersRepository {

    private val dao = ParlamentDatabase.getInstance(ParlamentApp.appContext).parlamentDatabaseDao
    val parlamentData: LiveData<List<ParlamentData>> = dao.getAllMembers()

    // Refreshing database from API
    suspend fun refreshParlamentData() {
        Timber.d("Refresh data is called");
        val members = ParlamentApi.retrofitService.getParlamentRecords()
        members.forEach { dao.insertOrUpdate(it) }
    }
}