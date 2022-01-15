package com.googlerepos.di

import com.googlerepos.api.retrofit.provideOkHttpClient
import com.googlerepos.api.retrofit.provideReposApi
import com.googlerepos.api.retrofit.provideRetrofit
import org.koin.dsl.module

val networkModule = module {
    factory { provideOkHttpClient() }
    factory { provideReposApi(get()) }
    single { provideRetrofit(get()) }
}