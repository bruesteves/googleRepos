package com.googlerepos.repos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.googlerepos.api.model.retrofit.Repo
import com.googlerepos.repos.repository.ReposRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReposViewModel(private val repo: ReposRepository) : ViewModel() {

    val repos: MutableLiveData<List<Repo>?> = MutableLiveData()

    fun getRepos() {
        CoroutineScope(Dispatchers.IO).launch {
            val request = repo.getRepos()

            withContext(Dispatchers.IO) {
                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                        repos.postValue(response.body())
                    } else {
                        repos.postValue(null)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    repos.postValue(null)
                }
            }
        }
    }
}