package tech.parzival48.thoeic.ui.game

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import tech.parzival48.thoeic.databinding.FragmentKeyboardBinding
import timber.log.Timber

class KeyboardFragment : Fragment() {

    private val binding: FragmentKeyboardBinding by lazy {
        FragmentKeyboardBinding.inflate(layoutInflater)
    }

    private val viewModel: GameViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(binding) {
            btnA.setOnClickListener(KeyboardClickListener())
            btnB.setOnClickListener(KeyboardClickListener())
            btnC.setOnClickListener(KeyboardClickListener())
            btnD.setOnClickListener(KeyboardClickListener())
            btnE.setOnClickListener(KeyboardClickListener())
            btnF.setOnClickListener(KeyboardClickListener())
            btnG.setOnClickListener(KeyboardClickListener())
            btnH.setOnClickListener(KeyboardClickListener())
            btnI.setOnClickListener(KeyboardClickListener())
            btnJ.setOnClickListener(KeyboardClickListener())
            btnK.setOnClickListener(KeyboardClickListener())
            btnL.setOnClickListener(KeyboardClickListener())
            btnM.setOnClickListener(KeyboardClickListener())
            btnN.setOnClickListener(KeyboardClickListener())
            btnO.setOnClickListener(KeyboardClickListener())
            btnP.setOnClickListener(KeyboardClickListener())
            btnQ.setOnClickListener(KeyboardClickListener())
            btnR.setOnClickListener(KeyboardClickListener())
            btnS.setOnClickListener(KeyboardClickListener())
            btnT.setOnClickListener(KeyboardClickListener())
            btnU.setOnClickListener(KeyboardClickListener())
            btnV.setOnClickListener(KeyboardClickListener())
            btnW.setOnClickListener(KeyboardClickListener())
            btnX.setOnClickListener(KeyboardClickListener())
            btnY.setOnClickListener(KeyboardClickListener())
            btnZ.setOnClickListener(KeyboardClickListener())
        }

    }

    private inner class KeyboardClickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            val btn = v as TextView
            btn.isEnabled = false
            viewModel.guessAlphabet(btn.text.first())
            btn.setTextColor(Color.parseColor("#252E30"))
        }
    }

}