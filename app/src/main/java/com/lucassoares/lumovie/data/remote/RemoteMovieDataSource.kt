package com.lucassoares.lumovie.data.remote

import com.lucassoares.lumovie.application.AppConstants
import com.lucassoares.lumovie.data.model.MovieList
import com.lucassoares.lumovie.repository.WebService

class RemoteMovieDataSource(private val webService: WebService) {

    suspend  fun getUpcomingMovie():MovieList = webService.getUpcomingMovie(AppConstants.API_KEY)

    suspend fun getTopRantedMovie():MovieList = webService.getTopRantedMovie(AppConstants.API_KEY)

   suspend fun getPopularMovie():MovieList = webService.getPopularMovie(AppConstants.API_KEY)

}