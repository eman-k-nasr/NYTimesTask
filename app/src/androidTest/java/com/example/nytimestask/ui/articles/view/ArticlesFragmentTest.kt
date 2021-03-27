package com.example.nytimestask.ui.articles.view

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
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
import org.mockito.Mockito.*

@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
class ArticlesFragmentTest{
    private lateinit var repository: IArticlesRepository
    private lateinit var articlesFromApi: List<Article>

    @Before
    fun initRepository() {
        articlesFromApi = listOf<Article>(
            Article(id = 1, title = "article1",source = "nytimes",byline = "person1",publishedDate = "23/3"),
            Article(id = 2, title = "article2",source = "nytimes",byline = "person2",publishedDate = "24/3"),
            Article(id = 3, title = "article3",source = "nytimes",byline = "person3",publishedDate = "25/3"),
            Article(id = 4, title = "article4",source = "nytimes",byline = "person4",publishedDate = "26/3"),
        )
        repository = FakeAndroidTestRepository(articlesFromApi)
        ServiceLocator.articlesRepository = repository
    }

    @Test
    fun testArticlesListVisible_ArticlesListVisibleOnAppLaunch(){
        launchFragmentInContainer<ArticlesFragment>(Bundle())
        onView(withId(R.id.articlesRecyclerView)).check(matches(isDisplayed()))
    }
    @Test
    fun clickArticle_navigateToDetailFragment() = runBlockingTest {
        val articlesFromApi = repository.fetchArticles()
        //given : on the home screen
        val scenario = launchFragmentInContainer<ArticlesFragment>(Bundle())
        val navController = mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        //when : Click on the third list item
        onView(withId(R.id.articlesRecyclerView))
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                hasDescendant(withText("article2")), click()))

        //then : verify that we navigate to the third detail screen
        verify(navController).navigate(
            ArticlesFragmentDirections.actionArticlesFragmentToDetailsFragment(articlesFromApi.data!!.get(1).id)
        )

    }

    @After
    fun reset() = ServiceLocator.resetRepository()
}