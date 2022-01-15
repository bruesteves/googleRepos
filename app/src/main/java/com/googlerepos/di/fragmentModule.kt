package com.googlerepos.di

import com.googlerepos.repos.ui.ReposFragment
import org.koin.dsl.module

val fragmentModule = module {
    factory { ReposFragment() }
}