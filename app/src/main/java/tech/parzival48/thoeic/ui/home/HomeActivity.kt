package tech.parzival48.thoeic.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import tech.parzival48.thoeic.databinding.ActivityFragmentContainerBinding
import tech.parzival48.thoeic.ui.component.SnackbarMaker

class HomeActivity : AppCompatActivity() {

    private val binding: ActivityFragmentContainerBinding by lazy {
        ActivityFragmentContainerBinding.inflate(layoutInflater)
    }

    private var doubleBackPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(binding.fragmentLayout.id, HomeFragment())
                .commit()
        }
    }

    override fun onBackPressed() {
        if(doubleBackPressedOnce) {
            super.onBackPressed()
            return
        }
        doubleBackPressedOnce = true
        SnackbarMaker.show(
            binding.root,
            "Click BACK again to exit",
            SnackbarMaker.TEMP
        )
        Handler().postDelayed({ doubleBackPressedOnce = false }, 2500)
    }

    companion object {
        fun createIntent(context: Context) : Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }
}