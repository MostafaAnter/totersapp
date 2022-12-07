package app.anter.core.bindings

import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import java.io.File

/**
 * Created by Mostafa Anter on 1/21/22.
 */

@BindingAdapter("htmlText", requireAll = false)
fun TextView.htmlText(htmlText: String?) {
    htmlText?.let {
        text = Html.fromHtml(htmlText)
    }
}
