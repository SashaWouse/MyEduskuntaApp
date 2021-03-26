package my.firstaapp.myeduskuntaapp.models.Parlament

import android.app.Application
import androidx.lifecycle.*
import my.firstaapp.myeduskuntaapp.database.ParlamentData
import my.firstaapp.myeduskuntaapp.database.ParlamentDatabaseDao
import my.firstaapp.myeduskuntaapp.repositories.ParlamentMembersRepository
import my.firstaapp.myeduskuntaapp.repositories.VotingRepository
import java.io.IOException

class ParlamentMembersViewModel(dataSource: ParlamentData, application: Application) : AndroidViewModel(application) {

    val parlamentMembersRepository = ParlamentMembersRepository
    var parlamentMembers = parlamentMembersRepository.parlamentData
    val selectedParty = Transformations.map(parlamentMembers){
        parlamentMembers.value?.filter { it.party == dataSource.party }
    }

    private val _navigateToSelectedMember = MutableLiveData<ParlamentData?>()
    val navigateToSelectedMember: LiveData<ParlamentData?>
        get() = _navigateToSelectedMember

    fun showSingleMember(memberData: ParlamentData) {
        _navigateToSelectedMember.value = memberData
    }

    fun singleMemberShown() {
        _navigateToSelectedMember.value = null
    }

}