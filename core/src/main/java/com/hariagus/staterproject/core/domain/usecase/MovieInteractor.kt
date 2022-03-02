package com.hariagus.staterproject.core.domain.usecase

import com.hariagus.staterproject.core.data.source.Resource
import com.hariagus.staterproject.core.domain.model.Movie
import com.hariagus.staterproject.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val iMovieRepository: IMovieRepository): MovieUseCase {

    override fun getAllMovies(sort: String): Flow<Resource<List<Movie>>> {
        return iMovieRepository.getAllMovies(sort)
    }

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return iMovieRepository.getFavoriteMovies()
    }

    override fun getSearchMovies(title: String): Flow<List<Movie>> {
        return iMovieRepository.getSearchMovie(title)
    }

    override fun setMovieFavorite(movie: Movie, state: Boolean) {
        return iMovieRepository.setMovieFavorite(movie, state)
    }
}