package com.example.nytimestask.data.repository

import com.example.nytimestask.data.FakeApi
import com.example.nytimestask.data.FakeDB
import com.example.nytimestask.data.local.DBHelper
import com.example.nytimestask.data.model.Article
import com.example.nytimestask.data.remote.ApiHelper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.IsEqual
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class ArticlesRepositoryTest{
    private lateinit var repo: ArticlesRepository
    private lateinit var fakeLocalDataSource: DBHelper
    private lateinit var fakeRemoteDataSource: ApiHelper
    private lateinit var articlesFromApi : List<Article>

    @Before
    fun setUpRepo(){
        articlesFromApi = listOf<Article>(
            Article(id = 1,title = "article1"),
            Article(id = 2,title = "article2"),
            Article(id = 3,title = "article3"),
            Article(id = 4,title = "article4"),
        )

        fakeLocalDataSource = FakeDB()
        fakeRemoteDataSource = FakeApi(articlesFromApi)
        repo = ArticlesRepository(fakeRemoteDataSource,fakeLocalDataSource)

    }

    @ExperimentalCoroutinesApi
    @Test
    fun getArticles_listOfArticles(){
        runBlockingTest {
            val articles = repo.fetchArticles()
            assertThat(articles.data,IsEqual(articlesFromApi))
        }
    }
}