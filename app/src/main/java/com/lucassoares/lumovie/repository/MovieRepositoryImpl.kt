package com.lucassoares.lumovie.repository

import com.lucassoares.lumovie.data.model.MovieList
import com.lucassoares.lumovie.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(private val dataSource : RemoteMovieDataSource):MovieRepository {
    override suspend fun getUpcomingMovie(): MovieList =  dataSource.getUpcomingMovie()

    override suspend fun getTopRantedMovie(): MovieList = dataSource.getTopRantedMovie()

    override suspend fun getPopularMovie(): MovieList = dataSource.getPopularMovie()

}