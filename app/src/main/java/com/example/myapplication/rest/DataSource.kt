package com.example.myapplication.rest

import com.example.pssexample.models.NewsResponse
import retrofit2.Response

class NewsRepo {
    suspend fun fetchNews(country: String, apiKey: String, category: String, q: String): Response<NewsResponse> {
        return RetrofitHelper.getInstance().create(NewsApiService::class.java).fetchNews(country, apiKey, category, q)
    }
}

