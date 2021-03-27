package com.example.nytimestask.ui.details.view

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nytimestask.data.model.Article
import com.example.nytimestask.data.repository.FakeAndroidTestRepository
import com.example.nytimestask.data.repository.IArticlesRepository
import com.example.nytimestask.data.servicelocator.ServiceLocator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsFragmentTest {
    private lateinit var repository: IArticlesRepository
    private lateinit var articlesFromApi: List<Article>

    @Before
    fun initRepository() {
        articlesFromApi = listOf<Article>(
            Article(id = 1, title = "article1"),
            Article(id = 2, title = "article2"),
            Article(id = 3, title = "article3"),
            Article(id = 4, title = "article4"),
        )
        repository = FakeAndroidTestRepository(articlesFromApi)
        ServiceLocator.articlesRepository = repository
    }

    @ExperimentalCoroutinesApi
    @Test
    fun articleDetails_DisplayedInUi() = runBlockingTest {
        //given : articleId That you will display details of
        val articleId = 3L
        val article = repository.fetchArticle(articleId)
        // when : details fragment launched to display article
        val bundle = Bundle()
        bundle.putLong("articleID", article.data!!.id)
        launchFragmentInContainer<DetailsFragment>(bundle)

    }

    @After
    fun reset() = ServiceLocator.resetRepository()

}