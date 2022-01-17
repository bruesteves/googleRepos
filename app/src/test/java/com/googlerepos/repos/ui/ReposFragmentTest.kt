package com.googlerepos.repos.ui

import com.googlerepos.api.model.retrofit.Owner
import com.googlerepos.api.model.retrofit.Repo
import com.googlerepos.repos.viewmodel.ReposViewModel
import com.mindera.rocketscience.launches.repository.ReposRepository
import kotlinx.android.synthetic.main.fragment_repos.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response


@RunWith(JUnit4::class)
class ReposFragmentTest {
    @Mock
    lateinit var repository: ReposRepository

    var fragment : ReposFragment = ReposFragment()

    var viewModel: ReposViewModel? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ReposViewModel(repository)
    }

    @Test
    fun testListSuccess(){
        `when`(repository.getRepos()).thenReturn(GlobalScope.async { mockList() })

        assertTrue(fragment.adapter.itemCount == 2)
        assertTrue(fragment.recyclerViewRepos.adapter?.itemCount == 2)
        assertTrue(fragment.adapter.reposList[0].name == "repoName")
        assertTrue(fragment.adapter.reposList[0].owner.login == "login")
    }

    fun mockList(): Response<List<Repo>> {
        return Response.success(
            listOf(
                Repo("repoName", "", Owner("login", "avatar.url"), "master", 0),
                Repo("repoName", "", Owner("login", "avatar.url"), "master", 0)
            )
        )
    }
}