package app.anter.feature_character_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import app.anter.feature_character_detail.databinding.DetailActivityBinding
import app.anter.feature_character_detail.ui.DetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailScreenActivity : AppCompatActivity() {

    //for bind view
    private lateinit var binding: DetailActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.detail_activity
        )
        if (savedInstanceState == null) {
            val fragment = DetailFragment.newInstance()
            val bundle = Bundle()
            bundle.putInt("id", intent.getIntExtra("id", 0))
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow()
        }
    }
}