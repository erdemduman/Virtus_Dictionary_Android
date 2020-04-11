package com.example.dictionaryapp.api.service

import com.example.dictionaryapp.model.SearchResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface ISearchApi {

    @GET("{lang}/{word}")
    fun meaning(@Path("lang") lang: String = "en", @Path("word") word: String?): Observable<List<SearchResponse>>

    companion object Factory {
        fun create(baseUrl: String): ISearchApi{
            val client: OkHttpClient = OkHttpClient()
                .newBuilder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .client(client)
                .build()

            return retrofit.create(ISearchApi::class.java)
        }
    }
}