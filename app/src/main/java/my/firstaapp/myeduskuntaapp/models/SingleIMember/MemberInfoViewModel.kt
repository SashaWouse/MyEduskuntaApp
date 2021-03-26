package my.firstaapp.myeduskuntaapp.models.SingleIMember

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import my.firstaapp.myeduskuntaapp.ParlamentApp
import my.firstaapp.myeduskuntaapp.database.ParlamentData
import my.firstaapp.myeduskuntaapp.repositories.ParlamentMembersRepository
import my.firstaapp.myeduskuntaapp.repositories.VotingRepository
import java.io.IOException

class MemberInfoViewModel(
    parlamentData: ParlamentData, application: Application) : AndroidViewModel(application) {

    private val votingRepository = VotingRepository
    private var votes = votingRepository.votingData
    var updatedVote = Transformations.map(votes){
        it.find { it.votingId == parlamentData.memberId }?.qualityRating
    }

    private val parlamentRepository = ParlamentMembersRepository
    private var members = parlamentRepository.parlamentData
    val memberSelected = members.value?.find { it.memberId == parlamentData.memberId }

    var name: String = ""
    var party: String = ""

    init {

        refreshVotesFromRepository()

        //Simpler for the fragment view when name is already "added" here
        name = "${memberSelected?.name} ${memberSelected?.surname}"

        // Setting the name of the party from the abbreviation to the full name
        party = when (memberSelected?.party) {

            "per" -> "Perussuomalaiset"
            "green" -> "Vihreä liitto"
            "ssd" -> "Suomen Sosialidemokraattinen Puolue"
            "center" -> "Suomen Keskusta"
            "kok" -> "Kansallinen Kokoomus"
            "vas" -> "Vasemmistoliitto"
            "sw" -> "Suomen ruotsalainen kansanpuolue"
            "sk" -> "Suomen Kristillisdemokraatit"

            else -> "..."
        }
    }

    // Refreshes Database from the API
    private fun refreshVotesFromRepository() {
        viewModelScope.launch {
            try {
                votingRepository.refreshVotingDataEntry()
            } catch (networkError: IOException) {
                Toast.makeText(
                    ParlamentApp.appContext, "$networkError",
                    Toast.LENGTH_LONG).show()
            }
        }
    }

    // Add vote to Repository and refreshes database
    fun liked(id: Int) {
        viewModelScope.launch {
            try {
                votingRepository.voteLikeData(id)
                refreshVotesFromRepository()
            } catch (networkError: IOException) {
                Toast.makeText(ParlamentApp.appContext, "$networkError",
                    Toast.LENGTH_LONG).show()
            }
        }

    }

    // Withdraw vote from Repository and refreshes database
    fun disliked(id: Int) {
        viewModelScope.launch {
            try {
                votingRepository.voteDislikeData(id)
                refreshVotesFromRepository()
            } catch (networkError: IOException) {
                Toast.makeText(ParlamentApp.appContext, "$networkError",
                    Toast.LENGTH_LONG).show()
            }
        }
    }
}

