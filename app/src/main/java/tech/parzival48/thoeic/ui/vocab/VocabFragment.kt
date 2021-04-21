package tech.parzival48.thoeic.ui.vocab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import tech.parzival48.thoeic.databinding.FragmentVocabBinding
import tech.parzival48.thoeic.ui.adapter.WordsAdapter
import tech.parzival48.thoeic.ui.adapter.WordsLoadStateAdapter

class VocabFragment : Fragment() {

    private val binding: FragmentVocabBinding by lazy {
        FragmentVocabBinding.inflate(layoutInflater)
    }

    private val viewModel: VocabViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val wordsAdapter = WordsAdapter()
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

}