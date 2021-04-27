package tech.parzival48.thoeic.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import tech.parzival48.thoeic.databinding.FragmentHomeBinding
import tech.parzival48.thoeic.ui.game.GameActivity
import tech.parzival48.thoeic.ui.popup.AboutDialogFragment
import tech.parzival48.thoeic.ui.vocab.VocabActivity

class HomeFragment : Fragment() {

	private val binding: FragmentHomeBinding by lazy {
		FragmentHomeBinding.inflate(layoutInflater)
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
		with(binding) {
			btnStartGame.setOnClickListener(OnStartClickListener())
			btnVocab.setOnClickListener(OnVocabClickListener())
			btnAbout.setOnClickListener(OnAboutClickListener())
			btnRate.setOnClickListener(OnRateClickListener())
		}
	}

	private inner class OnStartClickListener : View.OnClickListener {
		override fun onClick(v: View?) {
			startActivity(GameActivity.createIntent(requireContext()))
		}
	}

	private inner class OnVocabClickListener : View.OnClickListener {
		override fun onClick(v: View?) {
			startActivity(VocabActivity.createIntent(requireContext()))
		}
	}

	private inner class OnAboutClickListener : View.OnClickListener {
		override fun onClick(v: View?) {
			AboutDialogFragment().show(parentFragmentManager, "popup")
		}
	}

	private inner class OnRateClickListener : View.OnClickListener {
		override fun onClick(v: View?) {
			val intent = Intent(Intent.ACTION_VIEW)
			intent.data = Uri.parse("market://details?id=tech.parzival48.thoeic")
			startActivity(intent)
		}
	}

}