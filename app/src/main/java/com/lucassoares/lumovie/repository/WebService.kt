package com.lucassoares.lumovie.repository

import com.google.gson.GsonBuilder
import com.lucassoares.lumovie.application.AppConstants
import com.lucassoares.lumovie.data.model.MovieList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("movie/upcoming")
    suspend fun getUpcomingMovie(@Query ("api_key") apiKey : String): MovieList
    @GET("movie/top_rated")
    suspend fun getTopRantedMovie(@Query("api_key") apiKey: String): MovieList
    @GET("movie/popular")
    suspend fun getPopularMovie(@Query("api_key") apiKey: String): MovieList
}

object RetroFitClient{

    val webservice by lazy {

        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }

}