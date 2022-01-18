package com.googlerepos.di

import com.googlerepos.repos.repository.ReposRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { ReposRepository(get()) }
}