package com.example.dictionaryapp.api.service

import com.example.dictionaryapp.model.SearchResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ISearchApi {

    @GET("{lang}/{word}")
    fun meaning(@Path("lang") lang: String = "en", @Path("word") word: String?): Observable<List<SearchResponse>>

    companion object Factory {
        fun create(baseUrl: String): ISearchApi{
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build()

            return retrofit.create(ISearchApi::class.java)
        }
    }
}