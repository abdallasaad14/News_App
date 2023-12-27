package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentSavedNewsBinding
import com.example.newsapp.ui.MainActivity
import com.example.newsapp.ui.adapters.ArticleAdapter
import com.example.newsapp.ui.db.ArticleDatabase
import com.example.newsapp.ui.repo.NewsRepository
import com.example.newsapp.ui.viewModels.NewsViewModel
import com.example.newsapp.ui.viewModels.NewsViewModelFactory

@Suppress("DEPRECATION")
class SavedNewsFragment : Fragment() {
    lateinit var binding: FragmentSavedNewsBinding
    lateinit var viewModel: NewsViewModel
    var articleAdapter = ArticleAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedNewsBinding.inflate(inflater)
        binding.rvSavedNews.apply {
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        articleAdapter.setItemOnClickListener {
            val bundle = Bundle().apply { putSerializable("article", it) }
            findNavController().navigate(R.id.action_savedNewsFragment_to_articleFragment, bundle)
        }

        val repository = NewsRepository(ArticleDatabase.getInstance(requireContext()))
        val viewModelFactory = NewsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
        viewModel.getSavedArticles().observe(viewLifecycleOwner) { data ->
            articleAdapter.list = data
        }
        val itemTouchHelperCallBack = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = articleAdapter.list[position]
                viewModel.delete(article)


            }
        }
        ItemTouchHelper(itemTouchHelperCallBack).apply { attachToRecyclerView(binding.rvSavedNews) }
        return binding.root
    }
}