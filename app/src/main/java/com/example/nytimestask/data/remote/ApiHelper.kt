package com.example.nytimestask.data.remote

import com.example.nytimestask.data.model.Article
import com.example.nytimestask.utils.Resource

interface ApiHelper {
    suspend fun getArticles(): Resource<List<Article>>
}