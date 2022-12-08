package app.anter.feature_character_detail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.anter.core.util.NetworkHelper
import app.anter.core.util.Resource
import app.anter.core_data.remote.responses.characterDetailResponse.CharacterDetailResponse
import app.anter.core_data.repository.AppRepository
import app.anter.core_domain.interactors.DetailInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailInteractor: DetailInteractor
    ) : ViewModel() {

    private val _detail_response =
        MutableStateFlow<Resource<CharacterDetailResponse>>(Resource.firstOpen(null))
    val detail_response: StateFlow<Resource<CharacterDetailResponse>>
        get() = _detail_response

    fun getDetail(id: String) {
        viewModelScope.launch {
            _detail_response.value = Resource.loading(null)
            _detail_response.value = detailInteractor.execute(id)
        }
    }






}