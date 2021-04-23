package tech.parzival48.thoeic.ui.popup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import tech.parzival48.thoeic.R
import tech.parzival48.thoeic.databinding.FragmentLoadingDialogBinding

class LoadingDialogFragment : DialogFragment() {

	private val binding: FragmentLoadingDialogBinding by lazy {
		FragmentLoadingDialogBinding.inflate(layoutInflater)
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
		dialog?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		dialog?.setCancelable(false)
		dialog?.window?.setBackgroundDrawableResource(R.color.transparent)
	}

}