package my.firstaapp.myeduskuntaapp.models

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
import my.firstaapp.myeduskuntaapp.adapters.PartiesAdapter
import my.firstaapp.myeduskuntaapp.databinding.AllPartiesFragmentBinding
import my.firstaapp.myeduskuntaapp.models.AllPartiesFragmentDirections

class AllPartiesFragment : Fragment() {

    private lateinit var viewModel: AllPartiesViewModel
    private lateinit var binding: AllPartiesFragmentBinding
    private lateinit var adapterParty: PartiesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.all_parties_fragment, container, false)
        viewModel = ViewModelProvider(this).get(AllPartiesViewModel::class.java)
        adapterParty = PartiesAdapter(PartiesAdapter.OnClickListener {
            viewModel.showPartyItem(it)
        })

        binding.allParties.adapter = adapterParty
        binding.allParties.layoutManager = LinearLayoutManager(ParlamentApp.appContext)

        binding.allParties.addItemDecoration(DividerItemDecoration(ParlamentApp.appContext, DividerItemDecoration.VERTICAL))

        viewModel.partymember.observe(viewLifecycleOwner, {
            adapterParty.submitList(it)
        })

        // parliamentData can be used there on next fragment.
        viewModel.navigateToSelectedParty.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                // Must find the NavController from the Fragment
                this.findNavController().navigate(AllPartiesFragmentDirections.actionShowMembers(it))
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.partyItemIsShown()
            }
        })
        return binding.root
    }

}