package my.firstaapp.myeduskuntaapp.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import my.firstaapp.myeduskuntaapp.database.ParlamentData
import my.firstaapp.myeduskuntaapp.repositories.ParlamentMembersRepository

class AllPartiesViewModel(application: Application) : AndroidViewModel(application) {

    // Getting the data from ParliamentRepository
    val parlamentRepository = ParlamentMembersRepository

    val members = parlamentRepository.parlamentData
    val partymember = Transformations.map(members) {
        members.value?.distinctBy { it.party }
    }

    // LiveData
    private val _navigateToSelectedParty = MutableLiveData<ParlamentData?>()
    val navigateToSelectedParty: LiveData<ParlamentData?>
        get() = _navigateToSelectedParty

    fun showPartyItem(memberData: ParlamentData) {
        _navigateToSelectedParty.value = memberData
    }

    //navigateToSelectedParty is set to null
    fun partyItemIsShown() {
        _navigateToSelectedParty.value = null
    }
}