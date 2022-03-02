package com.hariagus.staterproject.core.data.source.remote.network

import com.hariagus.staterproject.core.BuildConfig
import com.hariagus.staterproject.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): ListMovieResponse
}