package my.firstaapp.myeduskuntaapp.models.SingleIMember

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import my.firstaapp.myeduskuntaapp.database.ParlamentData

class MemberInfoViewModelFactory(
        private val dataSource: ParlamentData,
        private val application: Application) : ViewModelProvider.Factory {

    @Suppress("unchecked cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemberInfoViewModel::class.java)) {
            return MemberInfoViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
