package app.anter.core_data.remote.responses.charactersResponse


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Url(
    @Json(name = "type")
    var type: String? = "",
    @Json(name = "url")
    var url: String? = ""
)