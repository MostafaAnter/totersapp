package app.anter.core_data.remote.responses.charactersResponse


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Item(
    @Json(name = "resourceURI")
    var resourceURI: String? = "",
    @Json(name = "name")
    var name: String? = ""
)