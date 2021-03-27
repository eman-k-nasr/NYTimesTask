package com.example.nytimestask.ui.articles.viewmodel

import androidx.lifecycle.*
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

@Suppress("UNCHECKED_CAST")
class ArticlesViewModelFactory(private val repo: IArticlesRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ArticlesViewModel::class.java)){
            return ArticlesViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}