package com.example.nytimestask.data.repository

import com.example.nytimestask.data.model.Article
import com.example.nytimestask.utils.Resource

interface IArticlesRepository {
    suspend fun fetchArticles(): Resource<List<Article>>
    suspend fun fetchArticle(articleId: Long): Resource<Article>
}