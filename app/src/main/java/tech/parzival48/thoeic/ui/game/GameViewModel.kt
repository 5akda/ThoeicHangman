package tech.parzival48.thoeic.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import tech.parzival48.thoeic.network.WordApiService
import tech.parzival48.thoeic.repository.WordDataSource

class GameViewModel(apiService: WordApiService) : ViewModel() {

	private val wordDataSource = WordDataSource(apiService)

	fun getWord() = liveData {
		emit(wordDataSource.getWord())
	}

}