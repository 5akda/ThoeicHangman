package tech.parzival48.thoeic.ui.vocab

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import tech.parzival48.thoeic.databinding.FragmentVocabBinding
import tech.parzival48.thoeic.model.Word
import tech.parzival48.thoeic.ui.adapter.WordsAdapter
import tech.parzival48.thoeic.ui.adapter.WordsLoadStateAdapter

class VocabFragment : Fragment() {

    private val binding: FragmentVocabBinding by lazy {
        FragmentVocabBinding.inflate(layoutInflater)
    }

    private val viewModel: VocabViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val wordsAdapter = WordsAdapter(OnItemClickListener())
        binding.recyclerView.also {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.setHasFixedSize(true)
            it.adapter = wordsAdapter.withLoadStateHeaderAndFooter(
                header = WordsLoadStateAdapter { wordsAdapter.retry() },
                footer = WordsLoadStateAdapter { wordsAdapter.retry() }
            )
        }

        lifecycleScope.launch {
            viewModel.vocabulary.collectLatest {
                wordsAdapter.submitData(it)
            }
        }
    }

    private inner class OnItemClickListener : WordsAdapter.OnWordClickListener{
        override fun onClick(word: Word) {
            MaterialAlertDialogBuilder(requireActivity())
                .setTitle("${word.english} (${word.partOfSpeech})")
                .setMessage("คำแปล: ${word.meaning}\nคำคล้าย: ${word.synonym}")
                .setPositiveButton("เปิดดูตัวอย่างประโยค", OnSeeMoreClickListener(word.english))
                .show()
        }
    }

    private inner class OnSeeMoreClickListener(private val english: String) : DialogInterface.OnClickListener {
        override fun onClick(dialog: DialogInterface?, which: Int) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://sentence.yourdictionary.com/${english}")
            startActivity(intent)
        }
    }

}