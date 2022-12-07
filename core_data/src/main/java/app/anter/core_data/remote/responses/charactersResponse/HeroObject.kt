package app.anter.core_data.remote.responses.charactersResponse


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity
class HeroObject(
    @Json(name = "id")
    @PrimaryKey
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
    var resourceURI: String? = ""
)