package my.firstaapp.myeduskuntaapp.models.SingleIMember

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import my.firstaapp.myeduskuntaapp.R
import my.firstaapp.myeduskuntaapp.adapters.bindImage
import my.firstaapp.myeduskuntaapp.databinding.MemberInfoFragmentBinding


class MemberInfoFragment : Fragment() {

    private lateinit var viewModel: MemberInfoViewModel
    private lateinit var binding: MemberInfoFragmentBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application
        val parlamentData = MemberInfoFragmentArgs.fromBundle(requireArguments()).memberSelected
        val viewModelFactory = MemberInfoViewModelFactory(parlamentData, application)

        binding = DataBindingUtil.inflate(inflater, R.layout.member_info_fragment, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MemberInfoViewModel::class.java)

        binding.imageLike.setOnClickListener { thumbLike() }
        binding.imageDislike.setOnClickListener { thumbDislike() }

        // Add name, party and photo
        binding.tvName.text = viewModel.name
        binding.tvParty.text = viewModel.party
        bindImage(binding.imgPhoto, "https://avoindata.eduskunta.fi/${viewModel.memberSelected?.pictureUrl}")

        viewModel.updatedVote.observe(viewLifecycleOwner, Observer {
            binding.textVote.text = "$it"
        })
        return binding.root
    }

    private fun thumbLike() {
        viewModel.memberSelected?.let { viewModel.liked(it.memberId) }
        binding.imageDislike.visibility = View.GONE
        binding.imageLike.visibility = View.GONE
    }

    private fun thumbDislike() {
        viewModel.memberSelected?.let { viewModel.disliked(it.memberId) }
        binding.imageDislike.visibility = View.GONE
        binding.imageDislike.visibility = View.GONE
    }

}