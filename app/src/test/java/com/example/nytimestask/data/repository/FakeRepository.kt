package com.example.nytimestask.data.repository

import com.example.nytimestask.data.model.Article
import com.example.nytimestask.utils.ERROR_MSG
import com.example.nytimestask.utils.Resource

class FakeRepository(val articles : List<Article>?):IArticlesRepository {
    override suspend fun fetchArticles(): Resource<List<Article>> {
        if(articles!=null){
            return Resource.success(articles)
        }else{
            return Resource.error(ERROR_MSG,null)
        }
    }
}