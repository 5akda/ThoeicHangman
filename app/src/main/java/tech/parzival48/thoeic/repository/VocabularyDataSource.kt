package tech.parzival48.thoeic.repository

import tech.parzival48.thoeic.model.RespondedList
import tech.parzival48.thoeic.model.Word
import tech.parzival48.thoeic.network.ApiService

class VocabularyDataSource(private val apiService: ApiService) {

	suspend fun getVocabulary(): RespondedList<Word> {
		return apiService.getVocabulary()
	}

}