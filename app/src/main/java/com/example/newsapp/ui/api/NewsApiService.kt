package com.example.newsapp.ui.api

import com.example.newsapp.ui.dataClasses.NewsResponse
import com.example.newsapp.ui.util.Constants.Companion.API_KEY
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {


    @GET("v2/top-headlines")
     fun getBreakingNews(

        @Query("country")
        countryCode: String = "us"

     ): Deferred<NewsResponse>

    @GET("v2/everything")
     fun getSearchNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
     ):Deferred<NewsResponse>
}