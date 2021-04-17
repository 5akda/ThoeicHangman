package tech.parzival48.thoeic.network

import retrofit2.Retrofit
import retrofit2.http.GET
import tech.parzival48.thoeic.model.RespondedList
import tech.parzival48.thoeic.model.Word

interface VocabApiService {

    @GET("vocabulary")
    suspend fun getVocabulary(): RespondedList<Word>

    companion object {
        fun create(retrofit: Retrofit): VocabApiService {
            return retrofit.create(VocabApiService::class.java)
        }
    }
}