package com.example.nytimestask.data.servicelocator

import com.example.nytimestask.data.local.DBHelper
import com.example.nytimestask.data.local.DBHelperImp
import com.example.nytimestask.data.remote.ApiHelper
import com.example.nytimestask.data.remote.ApiHelperImpl
import com.example.nytimestask.data.remote.RetrofitClient
import com.example.nytimestask.data.repository.ArticlesRepository

object ServiceLocator {

    @Volatile
    var articlesRepository: ArticlesRepository? = null

    fun provideArticlesRepository(): ArticlesRepository{
        synchronized(this) {
            return articlesRepository ?: createArticlesRepository()
        }
    }

    private fun createArticlesRepository(): ArticlesRepository {
        val newRepo = ArticlesRepository(createRemoteDataSource(), createLocaLDataSource())
        articlesRepository = newRepo
        return newRepo
    }

    private fun createLocaLDataSource():DBHelper{
        return DBHelperImp()
    }

    private fun createRemoteDataSource():ApiHelper{
        return ApiHelperImpl(apiService = RetrofitClient.apiService)
    }
}