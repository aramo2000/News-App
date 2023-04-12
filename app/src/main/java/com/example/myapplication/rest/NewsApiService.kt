package com.example.myapplication.rest

import retrofit2.http.GET
import retrofit2.http.Query
import com.example.pssexample.models.NewsResponse
import retrofit2.Response


interface NewsApiService {

    @GET("top-headlines")
    suspend fun fetchNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Response<NewsResponse>

}
