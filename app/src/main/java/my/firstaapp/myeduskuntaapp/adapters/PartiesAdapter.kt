package my.firstaapp.myeduskuntaapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import my.firstaapp.myeduskuntaapp.database.ParlamentData
import my.firstaapp.myeduskuntaapp.databinding.FragmentPartyItemBinding

class PartiesAdapter (private val onClickListener: OnClickListener): ListAdapter<ParlamentData, PartiesAdapter.PartyViewHolder>(PartyDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartyViewHolder {
        val binding = FragmentPartyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PartyViewHolder(binding)
    }

    @SuppressLint("Parties")
    override fun onBindViewHolder(holder: PartyViewHolder, position: Int) {

        val partyItem = getItem(position)

        // Setting the name of the party from the abbreviation to the full name
        when (partyItem.party){
            "per" -> holder.binding.partyName.text = "Perussuomalaiset"
            "green" -> holder.binding.partyName.text = "Vihreä liitto"
            "ssd" -> holder.binding.partyName.text = "Suomen Sosialidemokraattinen Puolue"
            "center" -> holder.binding.partyName.text = "Suomen Keskusta"
            "kok" -> holder.binding.partyName.text = "Kansallinen Kokoomus"
            "vas" -> holder.binding.partyName.text = "Vasemmistoliitto"
            "sw" -> holder.binding.partyName.text = "Suomen ruotsalainen kansanpuolue"
            "sk" -> holder.binding.partyName.text = "Suomen Kristillisdemokraatit"
            else -> holder.binding.partyName.text = "..."
        }

        holder.itemView.setOnClickListener { onClickListener.onClick(partyItem) }
    }

    class PartyViewHolder(val binding: FragmentPartyItemBinding) : RecyclerView.ViewHolder(binding.root)

    companion object PartyDiffCallback: DiffUtil.ItemCallback<ParlamentData>() {
        override fun areItemsTheSame(oldItem: ParlamentData, newItem: ParlamentData): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: ParlamentData, newItem: ParlamentData): Boolean {
            return oldItem.memberId == newItem.memberId
        }
    }

    class OnClickListener(val clickListener: (parliamentParty:ParlamentData) -> Unit) {
        fun onClick(parliamentParty:ParlamentData) = clickListener(parliamentParty)
    }
}