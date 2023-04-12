package com.example.myapplication.rest

import com.example.pssexample.models.NewsResponse
import retrofit2.Response

class NewsRepo(private val newsApiService: NewsApiService) {
    suspend fun fetchNews(country: String, apiKey: String): Response<NewsResponse> {
        return RetrofitHelper.getInstance().create(NewsApiService::class.java).fetchNews(country, apiKey)
    }
}

