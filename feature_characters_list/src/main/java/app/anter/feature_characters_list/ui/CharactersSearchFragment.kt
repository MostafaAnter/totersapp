package app.anter.feature_characters_list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import app.anter.core_data.remote.responses.charactersResponse.HeroObject
import app.anter.core_presentation.navigations.Navigations
import app.anter.feature_characters_list.R
import app.anter.feature_characters_list.databinding.CharactersListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class CharactersSearchFragment : Fragment() {

    companion object {
        fun newInstance() = CharactersSearchFragment()
    }

    //inject action for navigate to detail
    @Inject lateinit var actions: Navigations
    //inject view model by hilt DI
    private val viewModel: GamesSearchViewModel by viewModels()

    //for bind view with fragment
    private lateinit var binding: CharactersListFragmentBinding

    //init data for recyclerview
    private var mAdapter: GamesSearchResultListAdapter? = null
    private val modelList = ArrayList<HeroObject>()

    //for handle search job
    private var searchJob: Job? = null

    private fun search(query: String?) {
        // Make sure we cancel the previous job before creating a new one
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchRepo(query).collectLatest {
                mAdapter!!.submitData(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.characters_list_fragment,
            container,
            false
        )
        return binding.root
    }

    //for avoiding memory leak to improve performance
    override fun onStop() {
        super.onStop()
        //clear view attached with fragment
        binding.unbind()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //prepare recycler view
        setAdapter()

        //handle search button click
        handleSearchAction()

        //init search
        search(null)

    }

    private fun setAdapter() {
        mAdapter = GamesSearchResultListAdapter(requireActivity(), modelList)
        val layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = mAdapter

        //handle item click
        mAdapter!!.SetOnItemClickListener(object :
            GamesSearchResultListAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int, model: HeroObject?) {
                startActivity(
                    actions.openGameDetailScreenIntent(
                        requireActivity(),
                        model!!.id?: 0
                    )
                )
            }
        })

        mAdapter!!.addLoadStateListener {
            if (it.refresh == LoadState.Loading) {
                // show progress view
                binding.swipeRefreshRecyclerList.isEnabled = true
                binding.swipeRefreshRecyclerList.isRefreshing = true
            } else {
                //hide progress view
                binding.swipeRefreshRecyclerList.isRefreshing = false
                binding.swipeRefreshRecyclerList.isEnabled = false
            }

        }
    }

    private fun handleSearchAction(){
        binding.searchButton.setOnClickListener {
            val query = binding.enterQueryEditText.text.toString()
            if (SearchValidator().isValidQuery(query))
                search(query)
            else search(null)
        }
    }

}