package tech.parzival48.thoeic.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import tech.parzival48.thoeic.databinding.ItemLoadStateBinding
import tech.parzival48.thoeic.utils.visible

class WordsLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<WordsLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            ItemLoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            retry
        )
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bindState(loadState)
    }

    inner class LoadStateViewHolder(
        private val binding: ItemLoadStateBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindState(state: LoadState) {
            if (state is LoadState.Error) {
                binding.txtError.text = state.error.localizedMessage
            }
            binding.also {
                it.progressBar.visible(state is LoadState.Loading)
                it.btnRetry.visible(state is LoadState.Error)
                it.txtError.visible(state is LoadState.Error)
                binding.btnRetry.setOnClickListener { retry() }
            }
        }
    }


}