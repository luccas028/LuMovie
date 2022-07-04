package com.lucassoares.lumovie.data.local

import com.lucassoares.lumovie.application.AppConstants
import com.lucassoares.lumovie.data.model.MovieEntity
import com.lucassoares.lumovie.data.model.MovieList
import com.lucassoares.lumovie.data.model.toMovieList

class LocalMovieDataSource(private val movieDao: MovieDao) {
    suspend  fun getUpcomingMovie(): MovieList {
        return movieDao.getAllMovies().filter {it.movie_type =="upcoming"}.toMovieList()
    }

    suspend fun getTopRantedMovie(): MovieList {
        return movieDao.getAllMovies().filter {it.movie_type =="toprated"}.toMovieList()
    }

    suspend fun getPopularMovie(): MovieList {
        return movieDao.getAllMovies().filter {it.movie_type =="popular"}.toMovieList()
    }

    suspend fun saveMovie(movie:MovieEntity){
        movieDao.savedMovies(movie)
    }
}