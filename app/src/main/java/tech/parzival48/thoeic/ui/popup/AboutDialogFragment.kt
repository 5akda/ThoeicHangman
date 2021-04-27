package tech.parzival48.thoeic.ui.popup

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import tech.parzival48.thoeic.databinding.FragmentAboutDialogBinding
import timber.log.Timber

class AboutDialogFragment : DialogFragment() {

	private val binding: FragmentAboutDialogBinding by lazy {
		FragmentAboutDialogBinding.inflate(layoutInflater)
	}

	override fun onCreateView(
			inflater: LayoutInflater,
			container: ViewGroup?,
			savedInstanceState: Bundle?
	): View {
		return binding.root
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		binding.also {
			it.btnClose.setOnClickListener { dismiss() }
			it.btnEmail.setOnClickListener(OnEmailClickListener())
		}
	}

	private inner class OnEmailClickListener : View.OnClickListener {
		override fun onClick(v: View?) {
			val intent = Intent(Intent.ACTION_SEND)
			intent.data = Uri.parse("mailto:parzival48.tech@gmail.com")
			try {
			    startActivity(intent)
			} catch(e: ActivityNotFoundException) {
				Timber.d("ERROR! Activity no found.")
			}
		}

	}

}