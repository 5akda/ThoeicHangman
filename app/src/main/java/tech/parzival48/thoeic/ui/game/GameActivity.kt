package tech.parzival48.thoeic.ui.game

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import tech.parzival48.thoeic.R
import tech.parzival48.thoeic.databinding.ActivityGameBinding
import tech.parzival48.thoeic.utils.loadFromDrawable
import java.util.*

class GameActivity : AppCompatActivity() {

	private val binding: ActivityGameBinding by lazy {
		ActivityGameBinding.inflate(layoutInflater)
	}

	private val viewModel: GameViewModel by viewModel()

	private var doubleBackPressedOnce = false

	private val MAX_ATTEMPTS = 9

	private val hangmanImages = listOf(
            R.drawable.hangman_0,
            R.drawable.hangman_1,
            R.drawable.hangman_2,
            R.drawable.hangman_3,
            R.drawable.hangman_4,
            R.drawable.hangman_5,
            R.drawable.hangman_6,
            R.drawable.hangman_7,
            R.drawable.hangman_8
    )

	private lateinit var quizWord: String

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
		showLoading()
		if (savedInstanceState == null) {
			supportFragmentManager.beginTransaction()
					.add(binding.keyboardLayout.id, KeyboardFragment())
					.add(binding.successLayout.id, SuccessFragment())
					.add(binding.gameOverLayout.id, OverFragment())
					.commit()
		}
		subscribeLiveData()
	}

	override fun onBackPressed() {
		if (doubleBackPressedOnce) {
			super.onBackPressed()
			return
		}
		doubleBackPressedOnce = true
		showBackSnackbar()
		Handler().postDelayed({ doubleBackPressedOnce = false }, 2500)
	}

	private fun subscribeLiveData() {

		viewModel.words.observe(this, {
            quizWord = it[0].english
            viewModel.initDisplayString(quizWord)
            hideLoading()
        })

		viewModel.displayString.observe(this, {
            binding.txtDisplay.text = it
            if (it == quizWord.toUpperCase(Locale.ROOT)) {
                gameEnding(true)
            }
        })

		viewModel.numOfAttempts.observe(this, {
            if (it == MAX_ATTEMPTS) {
                gameEnding(false)
            } else {
                binding.imgHangman.loadFromDrawable(hangmanImages[it])
            }
        })
	}

	private fun hideLoading() {
		binding.loading.visibility = View.GONE
	}

	private fun showLoading() {
		binding.loading.visibility = View.VISIBLE
	}

	private fun gameEnding(success: Boolean) {
		with(binding) {
			keyboardLayout.visibility = View.GONE
			imgHangman.visibility = View.GONE
			txtDisplay.visibility = View.GONE
		}
		if (success) {
			binding.successLayout.visibility = View.VISIBLE
		} else {
			binding.gameOverLayout.visibility = View.VISIBLE
		}
	}

	private fun showBackSnackbar() {
		Snackbar.make(binding.root, "กด Back อีกครั้งเพื่อกลับไปยังหน้าเมนู", Snackbar.LENGTH_LONG)
				.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
				.setBackgroundTint(Color.parseColor("#eeeeee"))
				.setTextColor(Color.parseColor("#300303"))
				.show()
	}

	companion object {
		fun createIntent(context: Context): Intent {
			return Intent(context, GameActivity::class.java)
		}
	}

}