package com.example.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.ui.db.ArticleDatabase
import com.example.newsapp.ui.repo.NewsRepository
import com.example.newsapp.ui.viewModels.NewsViewModel
import com.example.newsapp.ui.viewModels.NewsViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val repository = NewsRepository(ArticleDatabase.getInstance(applicationContext))
        val viewModelFactory = NewsViewModelFactory(repository)
        var viewModel: NewsViewModel= ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]

        binding.bottomNavigationView.setupWithNavController(findNavController(R.id.myNavHostFragment))

    }
}