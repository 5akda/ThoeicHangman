package tech.parzival48.thoeic.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tech.parzival48.thoeic.network.WordApiService
import tech.parzival48.thoeic.repository.WordDataSource

class GameViewModel(apiService: WordApiService) : ViewModel() {


	private val wordSource = WordDataSource(apiService)

	init {
		viewModelScope.launch {

		}
	}

}