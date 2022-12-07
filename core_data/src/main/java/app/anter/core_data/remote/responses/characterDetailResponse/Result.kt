package app.anter.core_data.remote.responses.characterDetailResponse


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Result(
    @Json(name = "id")
    var id: Int? = 0,
    @Json(name = "name")
    var name: String? = "",
    @Json(name = "description")
    var description: String? = "",
    @Json(name = "modified")
    var modified: String? = "",
    @Json(name = "thumbnail")
    var thumbnail: Thumbnail? = Thumbnail(),
    @Json(name = "resourceURI")
    var resourceURI: String? = "",
    @Json(name = "comics")
    var comics: Comics? = Comics(),
    @Json(name = "series")
    var series: Series? = Series(),
    @Json(name = "stories")
    var stories: Stories? = Stories(),
    @Json(name = "events")
    var events: Events? = Events(),
    @Json(name = "urls")
    var urls: List<Url>? = listOf()
)