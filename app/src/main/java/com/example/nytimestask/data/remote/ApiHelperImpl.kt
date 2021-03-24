package com.example.nytimestask.data.remote

import com.example.nytimestask.data.model.ArticlesResult

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getArticlesResult(): ArticlesResult {
        return apiService.getArticlesResult()
    }
}