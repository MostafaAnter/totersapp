package app.anter.core_data.remote.responses.characterDetailResponse


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Data(
    @Json(name = "offset")
    var offset: Int? = 0,
    @Json(name = "limit")
    var limit: Int? = 0,
    @Json(name = "total")
    var total: Int? = 0,
    @Json(name = "count")
    var count: Int? = 0,
    @Json(name = "results")
    var results: List<Result>? = listOf()
)