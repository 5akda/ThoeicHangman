package tech.parzival48.thoeic.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import tech.parzival48.thoeic.R
import tech.parzival48.thoeic.databinding.FragmentSuccessBinding

class SuccessFragment : Fragment() {

    private val binding: FragmentSuccessBinding by lazy {
        FragmentSuccessBinding.inflate(layoutInflater)
    }

    private val viewModel: GameViewModel by sharedViewModel()

    private lateinit var answerSynonym: String

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
            val shuffledList = it.shuffled()
            answerSynonym = it[0].synonym
            with(binding) {
                txtEnglish.text = it[0].english
                txtMeaning.text = it[0].meaning
                txtPartOfSpeech.text = getString(R.string.partOfSpeech, it[0].partOfSpeech)
                btnChoice1.text = shuffledList[0].synonym
                btnChoice2.text = shuffledList[1].synonym
                btnChoice3.text = shuffledList[2].synonym
                btnChoice4.text = shuffledList[3].synonym
                txtAnswer.text = answerSynonym
            }
        })

        with(binding) {
            btnChoice1.setOnClickListener(SynonymClickListener())
            btnChoice2.setOnClickListener(SynonymClickListener())
            btnChoice3.setOnClickListener(SynonymClickListener())
            btnChoice4.setOnClickListener(SynonymClickListener())

            btnPlayAgain.setOnClickListener(PlayAgainClickListener())
        }
    }

    private inner class SynonymClickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            val btn = v as TextView
            if (btn.text == answerSynonym) {
                btn.setBackgroundColor(resources.getColor(R.color.correct))
                binding.txtGreat.text = getString(R.string.excellent)
                binding.synonymQuizLayout.visibility = View.GONE
                binding.endingLayout.visibility = View.VISIBLE
            } else {
                btn.setBackgroundColor(resources.getColor(R.color.incorrect))
                binding.txtInstruction.text = getString(R.string.wrong)
            }
        }
    }

    private inner class PlayAgainClickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            val intent = GameActivity.createIntent(requireContext())
            activity?.finish()
            startActivity(intent)
        }
    }

}