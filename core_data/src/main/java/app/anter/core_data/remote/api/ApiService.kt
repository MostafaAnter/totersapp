package app.anter.core_data.remote.api

import app.anter.core_data.BuildConfig
import app.anter.core_data.remote.responses.characterDetailResponse.CharacterDetailResponse
import app.anter.core_data.remote.responses.charactersResponse.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Mostafa Anter on 11/10/20.
 */
interface ApiService {
    @GET("/v1/public/characters")
    suspend fun searchCharacters(
        @Query("apikey") key: String = BuildConfig.PUBLIC_API_KEY,
        @Query("hash") hash: String = BuildConfig.HASH,
        @Query("ts") timestamp: Long = 1,
        @Query("limit") limit: Int = 100,
        @Query("nameStartsWith") query: String? = null,
        ): Response<CharactersResponse>

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterDetail(
        @Path("characterId") characterId: String,
        @Query("apikey") key: String = BuildConfig.PUBLIC_API_KEY,
    ): Response<CharacterDetailResponse>
}