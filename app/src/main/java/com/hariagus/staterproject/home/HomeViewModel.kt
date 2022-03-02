package com.hariagus.staterproject.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hariagus.staterproject.core.data.source.Resource
import com.hariagus.staterproject.core.domain.model.Movie
import com.hariagus.staterproject.core.domain.usecase.MovieUseCase

class HomeViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getMovies(sort: String): LiveData<Resource<List<Movie>>> =
        movieUseCase.getAllMovies(sort).asLiveData()
}