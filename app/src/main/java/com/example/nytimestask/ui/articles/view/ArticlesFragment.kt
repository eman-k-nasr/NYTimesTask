package com.example.nytimestask.ui.articles.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nytimestask.ArticlesApplication
import com.example.nytimestask.R
import com.example.nytimestask.data.model.Article
import com.example.nytimestask.ui.articles.adapter.ArticlesAdapter
import com.example.nytimestask.ui.articles.viewmodel.ArticlesViewModel
import com.example.nytimestask.ui.articles.viewmodel.ArticlesViewModelFactory
import com.example.nytimestask.utils.Status
import kotlinx.android.synthetic.main.fragment_articles.*

class ArticlesFragment : Fragment() {
    private lateinit var articlesViewModel: ArticlesViewModel
    private val TAG = "ArticlesFragment"
    private lateinit var articlesAdapter: ArticlesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_articles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setUpViewModel()
        setUpObserver()
    }

    private fun setupUI() {
        articlesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        articlesAdapter = ArticlesAdapter(arrayListOf())
        articlesRecyclerView.addItemDecoration(
            DividerItemDecoration(
                articlesRecyclerView.context,
                (articlesRecyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        articlesRecyclerView.adapter = articlesAdapter
    }

    private fun setUpViewModel(){
        articlesViewModel = ViewModelProviders.of(
            this,
            ArticlesViewModelFactory(
                ((requireContext().applicationContext) as ArticlesApplication).articlesRepository
            )
        ).get(ArticlesViewModel::class.java)
    }

    private fun setUpObserver(){
        articlesViewModel.getArticles().observe(requireActivity(), Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = GONE
                    it.data?.let { articles ->
                        updateAdater(articles)
                        Log.i(TAG,articles.toString())
                    }
                    articlesRecyclerView.visibility = VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = VISIBLE
                    articlesRecyclerView.visibility = GONE
                    Log.i(TAG, "Loading.....")
                }
                Status.ERROR -> {
                    progressBar.visibility = GONE
                    articlesRecyclerView.visibility = GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    Log.i(TAG, it.message.toString())
                }
            }
        })
    }

    private fun updateAdater(articles:List<Article>){
        articlesAdapter = ArticlesAdapter(articles)
        articlesRecyclerView.adapter = articlesAdapter
    }

}