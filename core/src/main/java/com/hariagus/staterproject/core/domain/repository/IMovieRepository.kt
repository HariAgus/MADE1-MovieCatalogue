package com.hariagus.staterproject.core.domain.repository

import com.hariagus.staterproject.core.data.source.Resource
import com.hariagus.staterproject.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getAllMovies(sort: String): Flow<Resource<List<Movie>>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun getSearchMovie(title: String): Flow<List<Movie>>

    fun setMovieFavorite(movie: Movie, state: Boolean)
}