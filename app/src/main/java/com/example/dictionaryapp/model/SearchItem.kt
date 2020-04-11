package com.example.dictionaryapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SearchResponse(
    @SerializedName("word") val word: String,
    @SerializedName("pronunciation") val pronunciation: String,
    @SerializedName("origin") val origin: String,
    @SerializedName("definitions") val definitions: List<DefinitionResponse>
) : Serializable

data class DefinitionResponse(
    @SerializedName("type") val type: String,
    @SerializedName("definition") val definition: String,
    @SerializedName("example") val example: String,
    @SerializedName("image_url") val image_url: String,
    @SerializedName("emoji") val emoji: String
) : Serializable