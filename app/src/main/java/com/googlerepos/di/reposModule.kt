package com.googlerepos.di

import com.googlerepos.repos.adapter.ReposAdapter
import org.koin.dsl.module

val reposModule = module {
    factory { ReposAdapter() }
}