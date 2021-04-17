package tech.parzival48.thoeic.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tech.parzival48.thoeic.model.Word
import tech.parzival48.thoeic.network.WordApiService

class WordDataSource(private val apiService: WordApiService) {

	suspend fun getRandomWord(): List<Word> {
		val shuffledPageNum = getShuffledPageNumber()
		return withContext(Dispatchers.IO) {
			listOf(
				apiService.getWord(shuffledPageNum[0], (0..29).random()),
				apiService.getWord(shuffledPageNum[1], (0..29).random()),
				apiService.getWord(shuffledPageNum[2], (0..29).random()),
				apiService.getWord(shuffledPageNum[3], (0..29).random()),
			)
		}
	}

	private fun getShuffledPageNumber(): List<Int> {
		return (1..4).toMutableList().shuffled()
	}

}