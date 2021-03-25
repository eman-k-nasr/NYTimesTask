package com.example.nytimestask

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.nytimestask.data.ArticlesRepository
import com.example.nytimestask.data.local.DBHelperImp
import com.example.nytimestask.data.remote.ApiHelperImpl
import com.example.nytimestask.data.remote.RetrofitClient
import com.example.nytimestask.ui.viewmodel.ArticlesViewModel
import com.example.nytimestask.utils.ArticlesViewModelFactory
import com.example.nytimestask.utils.Status

class MainActivity : AppCompatActivity() {
    private lateinit var articlesViewModel: ArticlesViewModel
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        articlesViewModel = ViewModelProviders.of(
            this,
             ArticlesViewModelFactory(
                     ArticlesRepository(
                             ApiHelperImpl(RetrofitClient.apiService),
                             DBHelperImp()
                     )
             )
        ).get(ArticlesViewModel::class.java)


        articlesViewModel.getArticles().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { articles ->
                        Log.i(TAG,articles.toString())
                    }
                }
                Status.LOADING -> {
                    Log.i(TAG, "Loading.....")
                }
                Status.ERROR -> {
                    Log.i(TAG, it.message.toString())
                }
            }
        })
    }
}