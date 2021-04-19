package tech.parzival48.thoeic.ui.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import tech.parzival48.thoeic.model.Word
import tech.parzival48.thoeic.network.WordApiService
import tech.parzival48.thoeic.repository.WordDataSource
import timber.log.Timber
import java.util.*

class GameViewModel(apiService: WordApiService) : ViewModel() {

	val words = MutableLiveData<List<Word>>()
	val displayString = MutableLiveData<String>()
	val numOfAttempts = MutableLiveData<Int>()
	private val MAX_ATTEMPTS = 8
	private val wordDataSource = WordDataSource(apiService)

	init {
		viewModelScope.launch {
			words.postValue(wordDataSource.getRandomWords())
			numOfAttempts.postValue(0)
		}
	}

	fun guessAlphabet(char: Char) {
		var temp = displayString.value
		words.value?.get(0)?.english?.toUpperCase(Locale.ROOT)?.let {
			for(i in it.indices) {
				if(it[i] == char) {
					temp = temp?.substring(0, i) + char + temp?.substring(i+1)
				} else {
					numOfAttempts.postValue(numOfAttempts.value?.plus(1))
				}
			}
		}
		displayString.postValue(temp?:"Error")
	}

	fun initDisplayString(quizWord: String) {
		var quizString = ""
		quizWord.forEach {
			quizString += "_"
		}
		displayString.postValue(quizString)
	}

}