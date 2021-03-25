package com.example.nytimestask.data

import com.example.nytimestask.data.local.DBHelper
import com.example.nytimestask.data.model.Article
import com.example.nytimestask.data.remote.ApiHelper
import com.example.nytimestask.utils.Resource

class ArticlesRepository(private val apiHelper: ApiHelper,
                         private val dbHepler: DBHelper) {

     suspend fun fetchArticles():Resource<List<Article>> = apiHelper.getArticles()

}