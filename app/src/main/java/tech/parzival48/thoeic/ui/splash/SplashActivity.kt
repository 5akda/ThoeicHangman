package tech.parzival48.thoeic.ui.splash

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import tech.parzival48.thoeic.BuildConfig
import tech.parzival48.thoeic.R
import tech.parzival48.thoeic.databinding.ActivitySplashBinding
import tech.parzival48.thoeic.network.NetworkProvider
import tech.parzival48.thoeic.ui.home.HomeActivity

class SplashActivity : AppCompatActivity() {


    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    private val viewModel: SplashViewModel by viewModel()

    private var backPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        drawAnimation()
    }

    override fun onResume() {
        super.onResume()
        checkServer()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        backPressed = true
    }

    private fun checkServer() {
        val cm = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
        if (viewModel.hasInternetConnection(cm as ConnectivityManager)) {
            observeNewerUpdate()
            observeUrl()
        } else {
            MaterialAlertDialogBuilder(this)
                .setTitle("No Internet Connection")
                .setMessage("เกมนี้ต้องการเชื่อมต่ออินเทอร์เน็ต กรุณาเปิดการเชื่อมต่อ")
                .setNeutralButton("Exit", OnExitClickListener())
                .setPositiveButton("Turn On", OnSetupClickListener())
                .setCancelable(false)
                .show()
        }
    }

    private fun drawAnimation() {
        viewModel.animationString.observe(this, {
            binding.txtHangman.text = it
        })
    }

    private fun redirectToHomeActivity() {
        Handler().postDelayed({
            if (backPressed.not()) {
                startActivity(HomeActivity.createIntent(this))
            }
            finish()
        }, 2500)
    }

    private fun observeNewerUpdate() {
        viewModel.latestVersion.observe(this, {
            if (BuildConfig.VERSION_CODE < it) {
                showUpdateSnackbar()
            }
        })
    }

    private fun observeUrl() {
        viewModel.baseUrl.observe(this, {
            NetworkProvider.setApiUrl(it)
            redirectToHomeActivity()
        })
    }

    private fun showUpdateSnackbar() {
        Snackbar.make(binding.root, "เกมนี้มีเวอร์ชั่นใหม่ออกแล้วนะ", Snackbar.LENGTH_LONG)
            //.setAction("อัพเดท!", OnUpdateClickListener())
            .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
            .setBackgroundTint(getColor(R.color.dimWhite))
            .setTextColor(getColor(R.color.textBlack))
            .show()
    }

    private inner class OnExitClickListener : DialogInterface.OnClickListener {
        override fun onClick(dialog: DialogInterface?, which: Int) {
            this@SplashActivity.finish()
        }
    }

    private inner class OnSetupClickListener : DialogInterface.OnClickListener {
        override fun onClick(dialog: DialogInterface?, which: Int) {
            startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS))
        }
    }

    private inner class OnUpdateClickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            backPressed = true
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://play.google.com/store/apps/")
            startActivity(intent)
        }

    }
}