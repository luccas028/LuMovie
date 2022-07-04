package com.lucassoares.lumovie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewmodel.CreationExtras
import com.lucassoares.lumovie.core.Resource
import com.lucassoares.lumovie.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import java.lang.Exception

class MovieViewModel(private val repo: MovieRepository) : ViewModel() {

    fun fetchMainScreenMovies() = liveData(Dispatchers.IO) {

        emit(Resource.Loading())

        try {
            emit(Resource.Succes(Triple(repo.getUpcomingMovie(),repo.getPopularMovie(),repo.getTopRantedMovie())))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

}

class MovieViewModelFactory(private val repo:MovieRepository):ViewModelProvider.Factory{
    override fun <T:ViewModel> create(modelClass: Class<T>):T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }


}


