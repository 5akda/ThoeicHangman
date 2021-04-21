package tech.parzival48.thoeic.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tech.parzival48.thoeic.databinding.ItemWordBinding
import tech.parzival48.thoeic.model.Word

class WordsAdapter : PagingDataAdapter<Word, WordsAdapter.WordViewHolder>(WordsComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder(ItemWordBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindWord(it)
        }
    }

    inner class WordViewHolder(private val binding: ItemWordBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindWord(item: Word) = with(binding) {
            txtEnglish.text = item.english
            txtPartOfSpeech.text = "( ${item.partOfSpeech} )"
            txtMeaning.text = item.meaning
            txtSynonym.text = item.synonym
        }
    }

    object WordsComparator : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.english == newItem.english
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word) = (oldItem == newItem)
    }
}