package tech.parzival48.thoeic.network

import retrofit2.Retrofit
import retrofit2.http.GET
import tech.parzival48.thoeic.model.RespondedList
import tech.parzival48.thoeic.model.Word

interface ApiService {

    @GET("vocabulary")
    suspend fun getVocabulary(): RespondedList<Word>

    @GET("vocabulary")
    suspend fun getWord(): Word


    companion object {
        fun create(retrofit: Retrofit): ApiService {
            return retrofit.create(ApiService::class.java)
        }
    }
}