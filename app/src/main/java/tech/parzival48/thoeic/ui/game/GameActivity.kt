package tech.parzival48.thoeic.ui.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import tech.parzival48.thoeic.R
import tech.parzival48.thoeic.databinding.ActivityGameBinding
import tech.parzival48.thoeic.ui.home.HomeFragment

class GameActivity : AppCompatActivity() {

    private val binding: ActivityGameBinding by lazy {
        ActivityGameBinding.inflate(layoutInflater)
    }

    private val viewModel: GameViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(binding.keyboardLayout.id, KeyboardFragment())
                .commit()
        }
    }

    private fun hideLoading() {
        binding.loading.visibility = View.GONE
    }

    companion object {
        fun createIntent(context: Context) : Intent {
            return Intent(context, GameActivity::class.java)
        }
    }

}