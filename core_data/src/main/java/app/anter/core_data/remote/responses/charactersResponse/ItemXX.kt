package app.anter.core_data.remote.responses.charactersResponse


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ItemXX(
    @Json(name = "resourceURI")
    var resourceURI: String? = "",
    @Json(name = "name")
    var name: String? = "",
    @Json(name = "type")
    var type: String? = ""
)