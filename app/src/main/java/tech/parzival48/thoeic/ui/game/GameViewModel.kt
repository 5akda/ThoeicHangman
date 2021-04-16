package tech.parzival48.thoeic.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import tech.parzival48.thoeic.network.ApiService
import tech.parzival48.thoeic.repository.WordDataSource

class GameViewModel(apiService: ApiService) : ViewModel() {

	private val wordDataSource = WordDataSource(apiService)

	fun getWord() = liveData {
		emit(wordDataSource.getWord())
	}

}