package com.googlerepos.api.retrofit

import com.googlerepos.api.model.retrofit.Repo
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ReposAPI {

    @GET("/users/google/repos")
    fun getRepos(): Deferred<Response<List<Repo>>>
}