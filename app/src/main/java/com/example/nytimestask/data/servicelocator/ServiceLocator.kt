package com.example.nytimestask.data.servicelocator

import androidx.annotation.VisibleForTesting
import com.example.nytimestask.data.local.DBHelper
import com.example.nytimestask.data.local.DBHelperImp
import com.example.nytimestask.data.remote.ApiHelper
import com.example.nytimestask.data.remote.ApiHelperImpl
import com.example.nytimestask.data.remote.RetrofitClient
import com.example.nytimestask.data.repository.ArticlesRepository
import com.example.nytimestask.data.repository.IArticlesRepository
import kotlinx.coroutines.runBlocking

object ServiceLocator {
    private val lock = Any()

    @Volatile
    var articlesRepository: IArticlesRepository? = null
        @VisibleForTesting set

    fun provideArticlesRepository(): IArticlesRepository{
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

    @VisibleForTesting
    fun resetRepository(){
        synchronized(lock){
           articlesRepository = null
        }
    }
}