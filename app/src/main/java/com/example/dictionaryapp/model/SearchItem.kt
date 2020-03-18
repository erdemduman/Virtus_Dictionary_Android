package com.example.dictionaryapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SearchResponse(
    @SerializedName("word") val word: String,
    @SerializedName("phonetic") val phonetic: String,
    @SerializedName("origin") val origin: String,
    @SerializedName("meaning") val meaning: Map<String, List<KindResponse>>
) : Serializable

data class KindResponse(
    @SerializedName("definition") val definition: String,
    @SerializedName("example") val example: String,
    @SerializedName("synonyms") val synonyms: List<String>
) : Serializable