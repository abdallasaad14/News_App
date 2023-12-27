package com.example.newsapp.ui.repo

import com.example.newsapp.ui.api.NewsApiClient
import com.example.newsapp.ui.api.NewsApiService
import com.example.newsapp.ui.dataClasses.Article
import com.example.newsapp.ui.db.ArticleDatabase
import retrofit2.Retrofit
import java.util.Locale.IsoCountryCode

class NewsRepository(val db: ArticleDatabase) {
    val api: NewsApiService by lazy {
        NewsApiClient().getClient().create(NewsApiService::class.java)
    }

    fun getBreakingNews(countryCode: String) = api.getBreakingNews(countryCode)
    fun searchNews(q: String)=api.getSearchNews(q)
    suspend fun insert(article:Article)=db.articleDao.insert(article)
    suspend fun delete(article:Article)=db.articleDao.delete(article)
    fun getAllSaved()=db.articleDao.getAllArticles()

}