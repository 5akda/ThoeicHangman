package tech.parzival48.thoeic.ui.home

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import tech.parzival48.thoeic.databinding.ActivityFragmentContainerBinding

class HomeActivity : FragmentActivity() {

    private val binding: ActivityFragmentContainerBinding by lazy {
        ActivityFragmentContainerBinding.inflate(layoutInflater)
    }

    private var doubleBackPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //overridePendingTransition(R.anim.fadein, R.anim.fadeout)
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
        showBackSnackbar()
        Handler().postDelayed({ doubleBackPressedOnce = false }, 2500)
    }

    private fun showBackSnackbar() {
        Snackbar.make(binding.root, "กด Back อีกครั้งเพื่อออกจากเกม", Snackbar.LENGTH_LONG)
            .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
            .setBackgroundTint(Color.parseColor("#eeeeee"))
            .setTextColor(Color.parseColor("#300303"))
            .show()
    }

    companion object {
        fun createIntent(context: Context) : Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }
}