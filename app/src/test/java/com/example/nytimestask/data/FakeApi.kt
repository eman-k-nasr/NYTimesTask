package com.example.nytimestask.data

import com.example.nytimestask.data.model.Article
import com.example.nytimestask.data.remote.ApiHelper
import com.example.nytimestask.utils.ERROR_MSG
import com.example.nytimestask.utils.Resource

class FakeApi(val articles : List<Article>?) : ApiHelper {

    override suspend fun getArticles(): Resource<List<Article>> {
        if(articles!=null){
            return Resource.success(articles)
        }else{
            return Resource.error(ERROR_MSG,null)
        }
    }

    override suspend fun fetchArticle(articleId: Long): Resource<Article> {
        val article = articles?.find { article -> article.id == articleId  }
        if(article!=null){
            return Resource.success(article)
        }else{
            return Resource.error(ERROR_MSG,null)
        }
    }

}