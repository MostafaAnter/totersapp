package app.anter.core_data.remote.api

import app.anter.core_data.remote.responses.characterDetailResponse.CharacterDetailResponse
import app.anter.core_data.remote.responses.charactersResponse.CharactersResponse
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Mostafa Anter on 11/10/20.
 */
class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun searchCharacters(query: String): Response<CharactersResponse> =
        apiService.searchCharacters(query = query)

    override suspend fun getCharacterDetail(characterId: String): Response<CharacterDetailResponse> =
        apiService.getCharacterDetail(characterId = characterId)
}