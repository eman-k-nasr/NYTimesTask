package com.example.nytimestask.ui.details.viewmodel

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
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class DetailsViewModelTest{
    private lateinit var articlesFromApi : List<Article>
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
      articlesFromApi = listOf<Article>(
            Article(id = 1,title = "article1"),
            Article(id = 2,title = "article2"),
            Article(id = 3,title = "article3"),
            Article(id = 4,title = "article4"),
        )
    }

    @Test
    fun givenServerResponseSuccess_getArticleDetails_shouldReturnSuccess(){
        val articleId = 3L
        val detailsViewModel = DetailsViewModel(FakeRepository(articlesFromApi),articleId)

        runBlockingTest {
            val observer = Observer<Resource<Article>> {}
            try{
                detailsViewModel.getArticleDetails().observeForever(observer)
                val value = detailsViewModel.getArticleDetails().value
                assertThat(value, IsEqual(
                    Resource.success(articlesFromApi.find {
                        article -> article.id == articleId
                    })
                ))
            }finally {
                detailsViewModel.getArticleDetails().removeObserver(observer)
            }
        }

    }

    @Test
    fun givenServerResponseError_getArticles_shouldReturnError(){
        val articleId = 8L
        val detailsViewModel = DetailsViewModel(FakeRepository(articlesFromApi),articleId)

        runBlockingTest {
            val observer = Observer<Resource<Article>> {}
            try{
                detailsViewModel.getArticleDetails().observeForever(observer)
                val value = detailsViewModel.getArticleDetails().value
                assertThat(value, IsEqual(Resource.error(ERROR_MSG,null)))
            }finally {
                detailsViewModel.getArticleDetails().removeObserver(observer)
            }
        }

    }

}