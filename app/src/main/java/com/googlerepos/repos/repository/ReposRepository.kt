package com.googlerepos.repos.repository

import com.googlerepos.api.retrofit.ReposAPI

class ReposRepository(private val api: ReposAPI) {
    fun getRepos() = api.getRepos()
}