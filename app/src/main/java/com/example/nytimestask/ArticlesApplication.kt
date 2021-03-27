package com.example.nytimestask

import android.app.Application
import com.example.nytimestask.data.repository.IArticlesRepository
import com.example.nytimestask.data.servicelocator.ServiceLocator


class ArticlesApplication : Application() {

    val articlesRepository: IArticlesRepository
        get() = ServiceLocator.provideArticlesRepository()


}