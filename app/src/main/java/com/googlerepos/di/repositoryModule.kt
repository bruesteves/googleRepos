package com.googlerepos.di

import com.mindera.rocketscience.launches.repository.ReposRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { ReposRepository(get()) }
}