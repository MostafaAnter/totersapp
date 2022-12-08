package app.anter.feature_characters_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.paging.ExperimentalPagingApi
import app.anter.feature_characters_list.databinding.CharactersListActivityBinding
import app.anter.feature_characters_list.ui.CharactersSearchFragment
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class MainScreenActivity : AppCompatActivity() {

    //for bind view with activity
    private lateinit var binding: CharactersListActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.characters_list_activity
        )
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CharactersSearchFragment.newInstance())
                .commitNow()
        }
    }
}