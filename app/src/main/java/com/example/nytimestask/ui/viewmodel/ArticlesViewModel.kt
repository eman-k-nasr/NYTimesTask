package com.example.nytimestask.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nytimestask.data.model.Article
import com.example.nytimestask.data.remote.ApiHelper
import com.example.nytimestask.utils.Resource
import kotlinx.coroutines.launch

class ArticlesViewModel(private val apiHelper:ApiHelper):ViewModel(){

    private val articles = MutableLiveData<Resource<List<Article>>>()

    init {
        fetchArticles()
    }

    private fun fetchArticles() {
        viewModelScope.launch {
            articles.postValue(Resource.loading(null))
            try {
                val articlesFromApi = apiHelper.getArticlesResult().results
                articles.postValue(Resource.success(articlesFromApi))
            } catch (e: Exception) {
                articles.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun getArticles(): LiveData<Resource<List<Article>>> {
        return articles
    }
}