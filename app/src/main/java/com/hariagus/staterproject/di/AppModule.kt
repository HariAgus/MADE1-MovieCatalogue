package com.hariagus.staterproject.di

import com.hariagus.staterproject.core.domain.usecase.MovieInteractor
import com.hariagus.staterproject.core.domain.usecase.MovieUseCase
import com.hariagus.staterproject.detail.DetailViewModel
import com.hariagus.staterproject.home.HomeViewModel
import com.hariagus.staterproject.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}