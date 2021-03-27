package com.example.nytimestask.ui.details.view

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nytimestask.R
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
            Article(id = 3, title = "article3", source = "nytimes",
                    byline = "eman", publishedDate = "27/3/2021",section = "Business",
                    adxKeywords = "key1,key2",type = "article"),
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
        // then : article details are displayed on the screen
        onView(withId(R.id.articleDetailsTitleTv)).check(matches(withText("article3")))
        onView(withId(R.id.articleDetailsSourceTv)).check(matches(withText("nytimes")))
        onView(withId(R.id.articleDetailsByLineTv)).check(matches(withText("eman")))
        onView(withId(R.id.articleDetailsDateTv)).check(matches(withText("27/3/2021")))
        onView(withId(R.id.articleDetailsSectionTv)).check(matches(withText("Business")))
        onView(withId(R.id.articleDetailsKeywordsTv)).check(matches(withText("key1,key2")))
        onView(withId(R.id.articleDetailsTypeTv)).check(matches(withText("Article")))
    }

    @After
    fun reset() = ServiceLocator.resetRepository()

}