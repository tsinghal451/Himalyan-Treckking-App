package com.example.himalayantracker

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("search")
    fun getNews(
        @Query("q") query: String,
        @Query("token") apiKey: String,
        @Query("lang") language: String,
        @Query("country") country: String,
        @Query("topic") category: String
    ): Call<NewsResponse>
}