package tech.parzival48.thoeic.ui.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tech.parzival48.thoeic.model.Word
import tech.parzival48.thoeic.network.WordApiService
import tech.parzival48.thoeic.repository.WordDataSource

class GameViewModel(apiService: WordApiService) : ViewModel() {

	private val wordDataSource = WordDataSource(apiService)
	val words = MutableLiveData<List<Word>>()


	init {
		viewModelScope.launch {
			words.postValue(wordDataSource.getRandomWord())
		}
	}

}