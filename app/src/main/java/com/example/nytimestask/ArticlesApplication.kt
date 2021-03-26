package com.example.nytimestask

import android.app.Application
import com.example.nytimestask.data.repository.ArticlesRepository
import com.example.nytimestask.data.servicelocator.ServiceLocator


class ArticlesApplication : Application() {

    val articlesRepository: ArticlesRepository
        get() = ServiceLocator.provideArticlesRepository()


}