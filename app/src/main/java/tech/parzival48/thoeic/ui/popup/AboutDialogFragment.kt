package tech.parzival48.thoeic.ui.popup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import tech.parzival48.thoeic.databinding.FragmentAboutDialogBinding

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
		}
	}

}