package tech.parzival48.thoeic.model

import com.google.gson.annotations.SerializedName

data class Word(
		@SerializedName("english")
		val english: String,
		@SerializedName("meaning")
		val meaning: String,
		@SerializedName("part_of_speech")
		val partOfSpeech: String,
		@SerializedName("synonym")
		val synonym: String
)