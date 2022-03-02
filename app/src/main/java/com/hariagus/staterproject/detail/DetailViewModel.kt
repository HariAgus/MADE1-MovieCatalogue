package com.hariagus.staterproject.detail

import androidx.lifecycle.ViewModel
import com.hariagus.staterproject.core.domain.model.Movie
import com.hariagus.staterproject.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun setFavoriteMovie(movie: Movie, newState: Boolean) {
        movieUseCase.setMovieFavorite(movie, newState)
    }
}