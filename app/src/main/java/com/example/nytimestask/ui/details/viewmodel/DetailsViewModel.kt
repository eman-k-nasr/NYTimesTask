package com.example.nytimestask.ui.details.viewmodel

import androidx.lifecycle.*
import com.example.nytimestask.data.model.Article
import com.example.nytimestask.data.repository.IArticlesRepository
import com.example.nytimestask.utils.Resource
import kotlinx.coroutines.launch

class DetailsViewModel(private val repo: IArticlesRepository,private val articleId: Long): ViewModel() {
    private val articleDetails = MutableLiveData<Resource<Article>>()

    init {
        fetchArticle(articleId)
    }

    private fun fetchArticle(articleId:Long) {
        viewModelScope.launch {
            articleDetails.postValue(Resource.loading(null))
            articleDetails.postValue(repo.fetchArticle(articleId))
        }
    }

    fun getArticleDetails(): LiveData<Resource<Article>> {
        return articleDetails
    }
}
@Suppress("UNCHECKED_CAST")
class DetailsViewModelFactory(private val repo: IArticlesRepository,private val articleId: Long)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailsViewModel::class.java)){
            return DetailsViewModel(repo,articleId) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}