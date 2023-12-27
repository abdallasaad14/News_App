package com.example.newsapp.ui.dataClasses

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)