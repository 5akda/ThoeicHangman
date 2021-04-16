package tech.parzival48.thoeic.ui.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import tech.parzival48.thoeic.databinding.ActivityFragmentContainerBinding

class GameActivity : AppCompatActivity() {

    private val binding: ActivityFragmentContainerBinding by lazy {
        ActivityFragmentContainerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }

    companion object {
        fun createIntent(context: Context) : Intent {
            return Intent(context, GameActivity::class.java)
        }
    }
}