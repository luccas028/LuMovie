package com.lucassoares.lumovie.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucassoares.lumovie.data.model.MovieEntity
import com.lucassoares.lumovie.data.model.MovieList

@Dao
interface MovieDao {
    @Query("SELECT * FROM movieentity")
    suspend fun getAllMovies(): List<MovieEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savedMovies(movie:MovieEntity)
}