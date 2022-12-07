package app.anter.core_data.remote.responses.characterDetailResponse


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Thumbnail(
    @Json(name = "path")
    var path: String? = "",
    @Json(name = "extension")
    var extension: String? = ""
)