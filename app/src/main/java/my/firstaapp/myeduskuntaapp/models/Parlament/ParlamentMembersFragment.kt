package my.firstaapp.myeduskuntaapp.models.Parlament

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import my.firstaapp.myeduskuntaapp.ParlamentApp
import my.firstaapp.myeduskuntaapp.R
import my.firstaapp.myeduskuntaapp.adapters.MembersAdapter
import my.firstaapp.myeduskuntaapp.databinding.FragmentAllMembersBinding


class ParlamentMembersFragment : Fragment() {

    private lateinit var viewModel: ParlamentMembersViewModel
    private lateinit var binding: FragmentAllMembersBinding
    private lateinit var membersAdapter: MembersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val application= requireNotNull(activity).application
        val arguments = ParlamentMembersFragmentArgs.fromBundle(requireArguments()).partySelected
        val viewModelFactory = ParlamentMembersViewModelFactory(arguments, application)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_members, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ParlamentMembersViewModel::class.java)
        membersAdapter = MembersAdapter( MembersAdapter.OnClickListener {
            viewModel.showSingleMember(it)
        })

        binding.allMembers.adapter = membersAdapter
        binding.allMembers.layoutManager = LinearLayoutManager(ParlamentApp.appContext)

        //Adds vertical divider for the itemViews
        binding.allMembers.addItemDecoration(DividerItemDecoration(ParlamentApp.appContext, DividerItemDecoration.VERTICAL))

        viewModel.selectedParty.observe(viewLifecycleOwner, {
            membersAdapter.submitList(it)
        })

        // Navigates to the next fragment and takes the chosen parliamentData to the next fragment, so it can be used there.
        viewModel.navigateToSelectedMember.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                // Must find the NavController from the Fragment
                this.findNavController().navigate(ParlamentMembersFragmentDirections.actionShowDetails(it))
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.singleMemberShown()
            }
        })

        return binding.root
    }

}