package my.firstaapp.myeduskuntaapp.models.Parlament

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import my.firstaapp.myeduskuntaapp.database.ParlamentData

class ParlamentMembersViewModelFactory(
    private val dataSource: ParlamentData,
    private val application: Application): ViewModelProvider.Factory {

    @Suppress("unchecked cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ParlamentMembersViewModel::class.java)) {
            return ParlamentMembersViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
