package com.hariagus.staterproject.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val overview: String,
    val originalLanguage: String,
    val title: String,
    val popularity: Double,
    val voteAverage: Double,
    val id: Int,
    val backdropPath: String,
    val releaseDate: String,
    val voteCount: Int,
    val posterPath: String,
    var isFavorite: Boolean,
    var isTvShow: Boolean
) : Parcelable
