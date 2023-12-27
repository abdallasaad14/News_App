package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentSearchNewsBinding
import com.example.newsapp.ui.adapters.ArticleAdapter
import com.example.newsapp.ui.db.ArticleDatabase
import com.example.newsapp.ui.repo.NewsRepository
import com.example.newsapp.ui.viewModels.NewsViewModel
import com.example.newsapp.ui.viewModels.NewsViewModelFactory

class SearchNewsFragment : Fragment() {
    lateinit var binding: FragmentSearchNewsBinding
    lateinit var viewModel: NewsViewModel
    var articleAdapter = ArticleAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchNewsBinding.inflate(inflater)
        binding.rvSearchNews.apply {
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(activity)

        }
        articleAdapter.setItemOnClickListener {
            val bundle=Bundle().apply { putSerializable("article",it) }
            findNavController().navigate(R.id.action_searchNewsFragment_to_articleFragment,bundle)
        }
        val repository = NewsRepository(ArticleDatabase.getInstance(requireContext()))
        val viewModelFactory = NewsViewModelFactory(repository)
        viewModel= ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
        binding.etSearch.addTextChangedListener {
          if (it!!.isNotEmpty())
            viewModel.getSearchNews(it.toString())

        }
        viewModel.searchNews.observe(viewLifecycleOwner) { data ->
            articleAdapter.list = data
        }
        return binding.root

    }
}