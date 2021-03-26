package com.example.nytimestask.ui.articles.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nytimestask.data.model.Article
import com.example.nytimestask.data.repository.IArticlesRepository
import com.example.nytimestask.utils.Resource
import kotlinx.coroutines.launch

class ArticlesViewModel(private val repo: IArticlesRepository):ViewModel(){

    private val articles = MutableLiveData<Resource<List<Article>>>()

    init {
        fetchArticles()
    }

    private fun fetchArticles() {
        viewModelScope.launch {
            articles.postValue(Resource.loading(null))
            articles.postValue(repo.fetchArticles())
        }
    }

    fun getArticles(): LiveData<Resource<List<Article>>> {
        return articles
    }
}