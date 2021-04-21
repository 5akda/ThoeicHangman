package tech.parzival48.thoeic.ui.vocab

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import tech.parzival48.thoeic.R

class VocabActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocab)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, VocabActivity::class.java)
        }
    }
}