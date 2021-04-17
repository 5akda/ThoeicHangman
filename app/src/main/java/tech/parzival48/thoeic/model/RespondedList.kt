package tech.parzival48.thoeic.model

import com.google.gson.annotations.SerializedName

data class RespondedList<T>(
    @SerializedName("size")
    val size: Int,
    @SerializedName("page_number")
    val pageNumber: Int,
    @SerializedName("total_page")
    val totalPage: Int,
    @SerializedName("content")
    val content: List<T>
)
