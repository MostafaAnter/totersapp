package app.anter.core_data.remote.responses.characterDetailResponse


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Comics(
    @Json(name = "available")
    var available: Int? = 0,
    @Json(name = "collectionURI")
    var collectionURI: String? = "",
    @Json(name = "items")
    var items: List<Item?>? = listOf(),
    @Json(name = "returned")
    var returned: Int? = 0
)