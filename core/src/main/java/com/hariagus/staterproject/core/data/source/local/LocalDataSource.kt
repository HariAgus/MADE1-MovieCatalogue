package com.hariagus.staterproject.core.data.source.local

import com.hariagus.staterproject.core.data.source.local.entity.MovieEntity
import com.hariagus.staterproject.core.data.source.local.room.MovieDao
import com.hariagus.staterproject.core.utils.SortUtils
import kotlinx.coroutines.flow.Flow

class LocalDataSource constructor(private val mMovieDao: MovieDao) {

    fun getAllMovies(sort: String): Flow<List<MovieEntity>> {
        val query = SortUtils.getSortedQueryMovies(sort)
        return mMovieDao.getAllMovies(query)
    }

    fun getAllFavoriteMovies(): Flow<List<MovieEntity>> {
        return mMovieDao.getFavoriteMovies()
    }

    fun setMovieFavorite(movie: MovieEntity, state: Boolean) {
        movie.isFavorite = state
        mMovieDao.updateDataMovie(movie)
    }

    fun getSearchMovies(title: String): Flow<List<MovieEntity>> {
        return mMovieDao.getSearchResult(title)
    }

    suspend fun insertMovie(movie: List<MovieEntity>) = mMovieDao.insertDataMovie(movie)

}