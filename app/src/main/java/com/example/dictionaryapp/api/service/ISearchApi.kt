package com.example.dictionaryapp.api.service

import com.example.dictionaryapp.model.SearchResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface ISearchApi {

    @Headers("Authorization: Token 8ac4b7369375cb10c2691664f5dafa3d43ebbfd6")
    @GET("{word}")
    fun meaning(
        @Path("word") word: String?
    ): Observable<SearchResponse>

    companion object Factory {
        fun create(baseUrl: String): ISearchApi {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build()

            return retrofit.create(ISearchApi::class.java)
        }
    }
}