package app.anter.core_data.local.database

import android.net.Uri
import androidx.room.TypeConverter
import app.anter.core_data.remote.responses.charactersResponse.Thumbnail
import java.util.*

/**
 * Created by Mostafa Anter on 3/17/21.
 * Type converters to allow Room to reference complex data types.
 */
class Converters {
    // region convert calendar
    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter fun datestampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }
    // endregion

    // region convert date
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
    //endregion

    // region convert image
    @TypeConverter
    fun fromThumbnail(value: Thumbnail?): String? {
        return value?.let { it.path + "/portrait_xlarge.${it.extension}" }
    }

    @TypeConverter
    fun dateToThumbnail(path: String?): Thumbnail {
        return Thumbnail(path = path)
    }
    //endregion

    //region convert Uri
    @TypeConverter
    fun fromString(value: String?): Uri? {
        return if (value == null) null else Uri.parse(value)
    }

    @TypeConverter
    fun toString(uri: Uri?): String? {
        return uri.toString()
    }
    //endregion
}