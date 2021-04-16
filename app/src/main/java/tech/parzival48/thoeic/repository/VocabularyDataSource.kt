package tech.parzival48.thoeic.repository

import tech.parzival48.thoeic.model.RespondedList
import tech.parzival48.thoeic.model.Word
import tech.parzival48.thoeic.network.WordApiService

class VocabularyDataSource(private val apiService: WordApiService) {

	suspend fun getVocabulary(): RespondedList<Word> {
		return apiService.getVocabulary()
	}

}