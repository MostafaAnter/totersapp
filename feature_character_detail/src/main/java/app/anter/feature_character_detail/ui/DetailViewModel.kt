package app.anter.feature_character_detail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.anter.core.util.NetworkHelper
import app.anter.core.util.Resource
import app.anter.core_data.remote.responses.characterDetailResponse.CharacterDetailResponse
import app.anter.core_data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val appRepository: AppRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _detail_response =
        MutableStateFlow<Resource<CharacterDetailResponse>>(Resource.firstOpen(null))
    val detail_response: StateFlow<Resource<CharacterDetailResponse>>
        get() = _detail_response

    fun getDetail(id: String) {
        viewModelScope.launch {
            _detail_response.value = Resource.loading(null)
            if (networkHelper.isNetworkConnected()) {
                appRepository.getCharacterDetail(id).let {
                    if (it.isSuccessful) {
                        _detail_response.value = Resource.success(it.body())
                    } else _detail_response.value = Resource.error(it.errorBody().toString(), null)
                }
            } else _detail_response.value = Resource.error("No internet connection", null)
        }
    }






}