package com.hariagus.staterproject.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hariagus.staterproject.core.domain.model.Movie
import com.hariagus.staterproject.core.domain.usecase.MovieUseCase

class SearchViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun searchForItems(title: String) : LiveData<List<Movie>> {
        return movieUseCase.getSearchMovies(title).asLiveData()
    }
}