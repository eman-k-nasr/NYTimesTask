package com.example.nytimestask.data.remote

import com.example.nytimestask.data.model.Article
import com.example.nytimestask.utils.Resource

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getArticles(): Resource<List<Article>> {
        try{
            val articlesFromApi= apiService.getArticlesResult().results
            return Resource.success(articlesFromApi)
        }catch(ex: Exception){
            return Resource.error(ex.toString(),null)
        }
    }

    override suspend fun fetchArticle(articleId: Long): Resource<Article> {
        try{
            val articlesFromApi= apiService.getArticlesResult().results
            val articleDetailsFromApi = articlesFromApi.find { article -> article.id == articleId }
            return Resource.success(articleDetailsFromApi)
        }catch(ex: Exception){
            return Resource.error(ex.toString(),null)
        }
    }
}
