package app.anter.core_data.remote.api

import app.anter.core_data.remote.responses.characterDetailResponse.CharacterDetailResponse
import app.anter.core_data.remote.responses.charactersResponse.CharactersResponse
import retrofit2.Response

/**
 * Created by Mostafa Anter on 11/10/20.
 */
interface ApiHelper {
    suspend fun searchCharacters(nameStartsWith: String): Response<CharactersResponse>
    suspend fun getCharacterDetail(characterId: String): Response<CharacterDetailResponse>
}