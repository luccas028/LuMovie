package com.lucassoares.lumovie.repository

import com.lucassoares.lumovie.data.model.MovieList

interface MovieRepository {
   suspend fun getUpcomingMovie(): MovieList
   suspend fun getTopRantedMovie():MovieList
   suspend fun getPopularMovie():MovieList
}