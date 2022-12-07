package app.anter.core_data.remote.responses.charactersResponse


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CharactersResponse(
    @Json(name = "code")
    var code: Int? = 0,
    @Json(name = "status")
    var status: String? = "",
    @Json(name = "copyright")
    var copyright: String? = "",
    @Json(name = "attributionText")
    var attributionText: String? = "",
    @Json(name = "attributionHTML")
    var attributionHTML: String? = "",
    @Json(name = "etag")
    var etag: String? = "",
    @Json(name = "data")
    var `data`: Data? = Data()
)