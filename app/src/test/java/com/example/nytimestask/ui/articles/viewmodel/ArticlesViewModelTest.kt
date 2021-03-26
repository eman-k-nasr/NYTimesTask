package com.example.nytimestask.ui.articles.viewmodel

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nytimestask.data.model.Article
import com.example.nytimestask.data.repository.FakeRepository
import com.example.nytimestask.utils.ERROR_MSG
import com.example.nytimestask.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.IsEqual
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class ArticlesViewModelTest{
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Test
    fun givenServerResponseSuccess_getArticles_shouldReturnSuccess(){

        val articlesFromApi = listOf<Article>(
            Article(id = 1,title = "article1"),
            Article(id = 2,title = "article2"),
            Article(id = 3,title = "article3"),
            Article(id = 4,title = "article4"),
        )
        val articlesViewModel = ArticlesViewModel(FakeRepository(articlesFromApi))

        runBlockingTest {
            val observer = Observer<Resource<List<Article>>> {}
            try{
                articlesViewModel.getArticles().observeForever(observer)
                val value = articlesViewModel.getArticles().value
                assertThat(value, IsEqual(Resource.success(articlesFromApi)))
            }finally {
                articlesViewModel.getArticles().removeObserver(observer)
            }
        }

    }

    @Test
    fun givenServerResponseError_getArticles_shouldReturnError(){
        val articlesViewModel = ArticlesViewModel(FakeRepository(null))

        runBlockingTest {
            val observer = Observer<Resource<List<Article>>> {}
            try{
                articlesViewModel.getArticles().observeForever(observer)
                val value = articlesViewModel.getArticles().value
                assertThat(value, IsEqual(Resource.error(ERROR_MSG,null)))
            }finally {
                articlesViewModel.getArticles().removeObserver(observer)
            }
        }

    }

}