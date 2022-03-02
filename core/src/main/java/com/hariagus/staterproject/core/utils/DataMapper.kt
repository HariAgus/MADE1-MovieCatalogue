package com.hariagus.staterproject.core.utils

import com.hariagus.staterproject.core.data.source.local.entity.MovieEntity
import com.hariagus.staterproject.core.data.source.remote.response.MovieResponse
import com.hariagus.staterproject.core.domain.model.Movie

object DataMapper {

    fun mapMovieResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                title = it.title,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                id = it.id,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                voteCount = it.voteCount,
                posterPath = it.posterPath,
                isFavorite = false,
                isTvShow = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                title = it.title,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                id = it.id,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                voteCount = it.voteCount,
                posterPath = it.posterPath,
                isFavorite = it.isFavorite,
                isTvShow = it.isTvShow
            )
        }

    fun mapDomainToEntities(input: Movie): MovieEntity {
        return MovieEntity(
            input.overview,
            input.originalLanguage,
            input.title,
            input.popularity,
            input.voteAverage,
            input.id,
            input.backdropPath,
            input.releaseDate,
            input.voteCount,
            input.posterPath,
            input.isFavorite,
            input.isTvShow
        )
    }

}
