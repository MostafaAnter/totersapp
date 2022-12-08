package app.anter.core_data.repository

import app.anter.core_data.remote.api.ApiHelper
import app.anter.core_data.remote.responses.characterDetailResponse.CharacterDetailResponse
import app.anter.core_data.remote.responses.charactersResponse.CharactersResponse
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Mostafa Anter on 3/17/21.
 */
class AppRepository @Inject constructor(
    private val apiHelper: ApiHelper
    ) {

    suspend fun searchCharacters(query: String?): Response<CharactersResponse> =
        apiHelper.searchCharacters(query)

    suspend fun getCharacterDetail(characterId: String): Response<CharacterDetailResponse> =
        apiHelper.getCharacterDetail(characterId)

}