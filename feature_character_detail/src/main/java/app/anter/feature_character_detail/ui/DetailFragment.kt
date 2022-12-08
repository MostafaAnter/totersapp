package app.anter.feature_character_detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.anter.core.util.Status
import app.anter.core_data.remote.responses.characterDetailResponse.CharacterDetailResponse
import app.anter.feature_character_detail.R
import app.anter.feature_character_detail.databinding.DetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private  val viewModel: DetailViewModel by viewModels ()

    //to hold user id
    private var itemID: Int = 0

    //bind data with view
    private lateinit var binding: DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.detail_fragment,
            container,
            false
        )
        return binding.root
    }

    // avoid memory leak
    override fun onStop() {
        super.onStop()
        binding.unbind()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        itemID = requireArguments().getInt("id")

        //observe coming data from server
        observeGameDetailResponse()

        //load game detail
        viewModel.getDetail(itemID.toString())


    }

    private fun bindDataWithView(model: CharacterDetailResponse){
        model.data?.results?.get(0)?.thumbnail?.path = model.data?.results?.get(0)?.thumbnail?.path + "/portrait_uncanny.${model.data?.results?.get(0)?.thumbnail?.extension}"
        binding.model = model
        binding.executePendingBindings()
    }

    private fun observeGameDetailResponse(){
        lifecycleScope.launch {
            viewModel.detail_response.collect {
                when(it.status) {
                    Status.FIRSTOPEN -> {}
                    Status.LOADING -> {}
                    Status.SUCCESS -> {
                        it.data?.let {
                            bindDataWithView(it)
                        }
                    }
                    Status.ERROR -> {}
                }
            }
        }
    }

}