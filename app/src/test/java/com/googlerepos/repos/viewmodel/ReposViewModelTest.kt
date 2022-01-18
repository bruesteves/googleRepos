package com.googlerepos.repos.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.googlerepos.api.model.retrofit.Owner
import com.googlerepos.api.model.retrofit.Repo
import com.googlerepos.api.retrofit.ReposAPI
import com.googlerepos.repos.repository.ReposRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.Response


@RunWith(JUnit4::class)
class ReposViewModelTest {
    @get:Rule
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: ReposRepository

    @Mock
    var apiClient: ReposAPI? = null

    var viewModel: ReposViewModel? = null

    @Mock
    lateinit var observer: Observer<in List<Repo>?>

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ReposViewModel(repository)
        viewModel!!.repos.observeForever(this.observer)
    }

    @Test
    fun testNull() {
        `when`(apiClient!!.getRepos()).thenReturn(null)
        assertTrue(viewModel!!.repos.hasObservers())
        assertTrue(viewModel!!.repos.value == null)
    }

    @Test
    fun testApiFetchDataSuccess() {
        `when`(apiClient!!.getRepos()).thenReturn(GlobalScope.async { mockList() })
        `when`(repository.getRepos()).thenReturn(GlobalScope.async { mockList() })
        viewModel!!.getRepos()
        Thread.sleep(500)
        verify(observer).onChanged(viewModel!!.repos.value)
        assertTrue(viewModel!!.repos.value?.get(0)!!.name == mockList().body()?.get(0)!!.name)
    }

    @Test
    fun testApiFetchDataFailure() {
        `when`(apiClient!!.getRepos()).thenReturn(GlobalScope.async { mockFailure() })
        `when`(repository.getRepos()).thenReturn(GlobalScope.async { mockFailure() })
        viewModel!!.getRepos()
        Thread.sleep(500)
        verify(observer).onChanged(viewModel!!.repos.value)
        assertTrue(viewModel!!.repos.value == null)
    }


    @After
    @Throws(Exception::class)
    fun tearDown() {
        apiClient = null
        viewModel = null
    }

    private fun mockList(): Response<List<Repo>> {
        return Response.success(
            listOf(
                Repo("repoName", "", Owner("login", "avatar.url"), "master", 0),
                Repo("repoName", "", Owner("login", "avatar.url"), "master", 0)
            )
        )
    }

    private fun mockFailure(): Response<List<Repo>> {
        return Response.error(404, null)
    }
}