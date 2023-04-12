package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.rest.NewsApiService
import com.example.myapplication.rest.NewsRepo
import com.example.myapplication.rest.RetrofitHelper
import com.example.pssexample.models.NewsResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class DataLoaderViewModel : ViewModel() {

    private val _articless = MutableLiveData<Result<Response<NewsResponse>>>()
    val articless: LiveData<Result<Response<NewsResponse>>> = _articless

    fun loadNews() {
        val newsApiService: NewsApiService =
            RetrofitHelper.getInstance().create(NewsApiService::class.java)
        viewModelScope.launch {
            try {
                val newsResponse = NewsRepo(newsApiService).fetchNews("us", "5a0575c4dcca45ee86873d0648f91869")
                _articless.postValue(Result.success(newsResponse))
            } catch (e: Exception) {
                _articless.postValue(Result.error(e))
            }
        }
    }
}


sealed class Result<out T : Any> {
    data class Loading(val message: String = "") : Result<Nothing>()
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    companion object {
        fun <T : Any> loading(message: String = ""): Result<T> = Loading(message)
        fun <T : Any> success(data: T): Result<T> = Success(data)
        fun error(exception: Exception): Result<Nothing> = Error(exception)
    }
}