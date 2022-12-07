package app.anter.core_data.remote.responses.characterDetailResponse


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Stories(
    @Json(name = "available")
    var available: Int? = 0,
    @Json(name = "collectionURI")
    var collectionURI: String? = "",
    @Json(name = "items")
    var items: List<ItemXX>? = listOf(),
    @Json(name = "returned")
    var returned: Int? = 0
)