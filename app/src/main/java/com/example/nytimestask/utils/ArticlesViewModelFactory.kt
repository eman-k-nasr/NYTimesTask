package com.example.nytimestask.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nytimestask.data.ArticlesRepository
import com.example.nytimestask.ui.viewmodel.ArticlesViewModel

class ArticlesViewModelFactory(private val repo:ArticlesRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ArticlesViewModel::class.java)){
            return ArticlesViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}