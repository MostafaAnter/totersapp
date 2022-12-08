package app.anter.core_presentation.navigations

import android.content.Context
import android.content.Intent
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Mostafa Anter.
 */
@Singleton
class Navigations @Inject constructor() {
    private fun internalIntent(context: Context, action: String) = Intent(action).setPackage(context.packageName)

    //open main screen feature
    fun openGameListScreenIntent(context: Context) = internalIntent(context, "app.anter.feature_characters_list_screen.open")

    //open detail screen feature
    fun openGameDetailScreenIntent(context: Context, id: Int) = internalIntent(context, "app.anter.feature_detail_screen.open").apply {
        putExtra("id", id)
    }
}