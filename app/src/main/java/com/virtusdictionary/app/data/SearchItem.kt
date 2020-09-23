package com.virtusdictionary.app.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SearchResponse(
    @SerializedName("word") val word: String,
    @SerializedName("phonetic") val phonetic: String,
    @SerializedName("origin") val origin: String,
    @SerializedName("meaning") val meaning: Map<String, ArrayList<KindResponse>>
) : Serializable

data class KindResponse(
    @SerializedName("definition") val definition: String,
    @SerializedName("example") val example: String,
    @SerializedName("synonyms") val synonyms: ArrayList<String>
) : Serializable