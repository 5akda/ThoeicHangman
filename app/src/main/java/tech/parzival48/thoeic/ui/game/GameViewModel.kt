package tech.parzival48.thoeic.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import tech.parzival48.thoeic.model.Word
import tech.parzival48.thoeic.network.WordApiService
import tech.parzival48.thoeic.repository.WordDataSource
import java.util.*

class GameViewModel(apiService: WordApiService) : ViewModel() {

	private val words = MutableLiveData<List<Word>>()
	private val displayString = MutableLiveData<String>()
	private val numOfAttempts = MutableLiveData<Int>()

	private val wordDataSource = WordDataSource(apiService)

	init {
		viewModelScope.launch {
			words.postValue(wordDataSource.getRandomWords())
			numOfAttempts.postValue(0)
		}
	}

	fun guessAlphabet(char: Char) {
		var temp = displayString.value
		var existed = false
		words.value?.get(0)?.english?.toUpperCase(Locale.ROOT)?.let {
			for (i in it.indices) {
				if (it[i] == char) {
					temp = temp?.substring(0, i) + char + temp?.substring(i + 1)
					existed = true
				}
			}
		}
		if (!existed) {
			numOfAttempts.value = numOfAttempts.value?.plus(1)
		}
		displayString.value = temp?:"error"
	}

	fun initDisplayString(quizWord: String?) {
		var quizString = ""
		repeat(quizWord?.length?:0) {
			quizString += "_"
		}
		displayString.postValue(quizString)
	}

	fun getWords(): LiveData<List<Word>> = words

	fun getDisplayString(): LiveData<String> = displayString

	fun getNumOfAttempts(): LiveData<Int> = numOfAttempts

}