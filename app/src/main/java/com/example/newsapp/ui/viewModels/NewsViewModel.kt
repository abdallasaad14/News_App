package com.example.newsapp.ui.viewModels

import android.text.Editable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.ui.dataClasses.Article
import com.example.newsapp.ui.repo.NewsRepository
import com.example.newsapp.ui.util.Constants.Companion.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale.IsoCountryCode

class NewsViewModel(val newsRepository: NewsRepository) : ViewModel() {


    private val _breakingNews = MutableLiveData<List<Article>>()

    // The external immutable LiveData for the request status String
    val breakingNews: LiveData<List<Article>>
        get() = _breakingNews

    private val _searchNews = MutableLiveData<List<Article>>()

    // The external immutable LiveData for the request status String
    val searchNews: LiveData<List<Article>>
        get() = _searchNews

    init {
        getBreakingNews()
    }

    private fun getBreakingNews() = viewModelScope.launch(Dispatchers.Main) {

        try {


            val list = newsRepository.getBreakingNews("us").await()

            _breakingNews.value = list.articles

        } catch (e: Exception) {
            Log.i(TAG, e.toString())
        }
    }

    fun getSearchNews(q: String) = viewModelScope.launch(Dispatchers.Main) {

        try {

            val list = newsRepository.searchNews(q).await()
            _searchNews.value = list.articles
        } catch (e: Exception) {
            Log.i(TAG, e.toString())
        }
    }

    fun save(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.insert(article)
        }



    }
    fun delete(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.delete(article)
        }
    }
    fun getSavedArticles() = newsRepository.getAllSaved()

}