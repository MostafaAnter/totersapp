package app.anter.core.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import java.io.File

/**
 * Created by Mostafa Anter.
 */

@BindingAdapter("circleImageUrl", requireAll = false)
fun ImageView.circleImageUrl(url: String?) {
    if (url!!.startsWith("http")) {
        load(url) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
    } else {
        load(File(url)) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
    }
}
