package app.anter.core_domain.interactors

import app.anter.core.util.NetworkHelper
import app.anter.core.util.Resource
import app.anter.core_data.remote.responses.characterDetailResponse.CharacterDetailResponse
import app.anter.core_data.repository.AppRepository
import javax.inject.Inject

/**
 * Created by Mostafa Anter on 12/8/22.
 */
class DetailInteractor @Inject constructor(
    private val appRepository: AppRepository,
    private val networkHelper: NetworkHelper
) {
    suspend fun execute(id: String):Resource<CharacterDetailResponse>{
        if (networkHelper.isNetworkConnected()) {
            appRepository.getCharacterDetail(id).let {
                if (it.isSuccessful) {
                   return Resource.success(it.body())
                } else return Resource.error(it.errorBody().toString(), null)
            }
        } else return Resource.error("No internet connection", null)
    }
}