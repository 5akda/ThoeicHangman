package tech.parzival48.thoeic.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import tech.parzival48.thoeic.R
import tech.parzival48.thoeic.databinding.FragmentGameOverBinding

class OverFragment : Fragment() {

    private val binding: FragmentGameOverBinding by lazy {
        FragmentGameOverBinding.inflate(layoutInflater)
    }

    private val viewModel: GameViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.words.observe(viewLifecycleOwner, {
            with(binding) {
                txtEnglish.text = it[0].english
                txtMeaning.text = it[0].meaning
                txtPartOfSpeech.text = getString(R.string.partOfSpeech, it[0].partOfSpeech)
            }
        })

        binding.btnPlayAgain.setOnClickListener(PlayAgainClickListener())

    }

    private inner class PlayAgainClickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            val intent = GameActivity.createIntent(requireContext())
            activity?.finish()
            startActivity(intent)
        }
    }

}