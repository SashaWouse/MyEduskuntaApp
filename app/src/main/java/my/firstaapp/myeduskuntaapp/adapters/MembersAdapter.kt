package my.firstaapp.myeduskuntaapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import my.firstaapp.myeduskuntaapp.database.ParlamentData
import my.firstaapp.myeduskuntaapp.databinding.ParlamentMembersFragmentBinding
import my.firstaapp.myeduskuntaapp.repositories.VotingRepository

class MembersAdapter(
        private val onClickListener: OnClickListener): ListAdapter<ParlamentData,
        MembersAdapter.MemberViewHolder>(MemberDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val binding = ParlamentMembersFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {

        val memberItem = getItem(position)

        // Member name
        holder.binding.mname.text = "${memberItem.name} ${memberItem.surname}"

        // Member image
        bindImage(holder.binding.mphoto, "https://avoindata.eduskunta.fi/${memberItem.pictureUrl}")
        // OnClick event for choosing a member and then transferring the info to MemberDetails fragment
        holder.itemView.setOnClickListener { onClickListener.onClick(memberItem) }
    }

    companion object MemberDiffCallback : DiffUtil.ItemCallback<ParlamentData>() {
        override fun areItemsTheSame(oldItem: ParlamentData, newItem: ParlamentData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ParlamentData, newItem: ParlamentData): Boolean {
            return oldItem.memberId == newItem.memberId
        }

    }

    class MemberViewHolder(val binding: ParlamentMembersFragmentBinding)
        :RecyclerView.ViewHolder(binding.root)

    class OnClickListener(val clickListener: (parlamentParty: ParlamentData) -> Unit) {
        fun onClick(parlamentParty: ParlamentData) = clickListener(parlamentParty)
    }

}



