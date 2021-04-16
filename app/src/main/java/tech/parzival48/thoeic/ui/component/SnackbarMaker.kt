package tech.parzival48.thoeic.ui.component

import android.graphics.Color
import android.view.View
import com.google.android.material.snackbar.Snackbar

object SnackbarMaker {

	const val TEMP = Snackbar.LENGTH_LONG
	const val FOREVER = Snackbar.LENGTH_INDEFINITE
    private const val ANIMATION = Snackbar.ANIMATION_MODE_SLIDE
    private const val BG_COLOR = "#eeeeee"
    private const val TXT_COLOR = "#300303"
    private const val ACTION_COLOR = "#de4747"

	fun show(parentView: View, message: String, duration: Int) {
		Snackbar.make(parentView, message, duration)
				.setAnimationMode(ANIMATION)
				.setBackgroundTint(Color.parseColor(BG_COLOR))
                .setTextColor(Color.parseColor(TXT_COLOR))
                .setActionTextColor(Color.parseColor(ACTION_COLOR))
				.show()
	}

	fun show(parentView: View, message: String, action: String, listener: View.OnClickListener, duration: Int) {
		Snackbar.make(parentView, message, duration)
                .setBackgroundTint(Color.parseColor(BG_COLOR))
                .setTextColor(Color.parseColor(TXT_COLOR))
                .setActionTextColor(Color.parseColor(ACTION_COLOR))
				.setAction(action, listener)
				.setAnimationMode(ANIMATION)
				.show()
	}
}