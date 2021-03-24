package com.example.nytimestask.data.remote

import com.example.nytimestask.data.model.ArticlesResult
import com.example.nytimestask.utils.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("viewed/7.json")
    suspend fun getArticlesResult(@Query("api-key") apiKey: String = API_KEY): ArticlesResult

}