package com.googlerepos.di

import com.googlerepos.repos.viewmodel.ReposViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        ReposViewModel(get())
    }
}