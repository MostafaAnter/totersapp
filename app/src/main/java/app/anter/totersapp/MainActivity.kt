package app.anter.totersapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.anter.core_presentation.navigations.Navigations
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // inject actions to navigate between  modules
    @Inject
    lateinit var navigations: Navigations

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // navigate to news search feature
        startActivity(
            navigations.openGameListScreenIntent(
                this
            )
        )
        finish()
    }
}