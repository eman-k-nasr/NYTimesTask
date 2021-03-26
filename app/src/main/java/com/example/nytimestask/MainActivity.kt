package com.example.nytimestask

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.nytimestask.data.repository.ArticlesRepository
import com.example.nytimestask.data.local.DBHelperImp
import com.example.nytimestask.data.remote.ApiHelperImpl
import com.example.nytimestask.data.remote.RetrofitClient
import com.example.nytimestask.ui.articles.viewmodel.ArticlesViewModel
import com.example.nytimestask.utils.ArticlesViewModelFactory
import com.example.nytimestask.utils.Status

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}