package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentBreakingNewsBinding
import com.example.newsapp.ui.MainActivity
import com.example.newsapp.ui.adapters.ArticleAdapter
import com.example.newsapp.ui.db.ArticleDatabase
import com.example.newsapp.ui.repo.NewsRepository
import com.example.newsapp.ui.viewModels.NewsViewModel
import com.example.newsapp.ui.viewModels.NewsViewModelFactory

class BreakingNewsFragment : Fragment() {
    lateinit var binding: FragmentBreakingNewsBinding
    lateinit var viewModel: NewsViewModel
    var articleAdapter = ArticleAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBreakingNewsBinding.inflate(inflater)
        articleAdapter.setItemOnClickListener {
            val bundle=Bundle().apply { putSerializable("article",it) }
        findNavController().navigate(R.id.action_breakingNewsFragment_to_articleFragment,bundle)
        }
        binding.rvBreakingNews.apply {
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        val repository = NewsRepository(ArticleDatabase.getInstance(requireContext()))
        val viewModelFactory = NewsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]

        viewModel.breakingNews.observe(viewLifecycleOwner) { data ->
            articleAdapter.list = data
        }

        return binding.root
    }

}