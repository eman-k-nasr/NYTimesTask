package com.example.nytimestask.data.remote

import com.example.nytimestask.data.model.ArticlesResult

interface ApiHelper {
    suspend fun getArticlesResult(): ArticlesResult
}