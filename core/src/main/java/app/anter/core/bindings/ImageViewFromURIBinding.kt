package app.anter.core.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation

/**
 * Created by Mostafa Anter.
 */

@BindingAdapter("normalImageUri", requireAll = false)
fun ImageView.normalImageUri(uri: String?) {
    load(uri) {
        crossfade(true)

    }
}

@BindingAdapter("roundedImageUri", requireAll = false)
fun ImageView.roundedImageUri(uri: String?) {
    load(uri) {
        crossfade(true)
        transformations(RoundedCornersTransformation())
    }
}
