package com.lucassoares.lumovie.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.lucassoares.lumovie.R
import com.lucassoares.lumovie.core.Resource
import com.lucassoares.lumovie.data.model.Movie
import com.lucassoares.lumovie.data.remote.RemoteMovieDataSource
import com.lucassoares.lumovie.databinding.FragmentMovieBinding
import com.lucassoares.lumovie.presentation.MovieViewModel
import com.lucassoares.lumovie.presentation.MovieViewModelFactory
import com.lucassoares.lumovie.repository.MovieRepositoryImpl
import com.lucassoares.lumovie.repository.RetroFitClient
import com.lucassoares.lumovie.ui.movie.adapter.MovieAdapter
import com.lucassoares.lumovie.ui.movie.adapter.concat.TopRatedConcatAdapter
import com.lucassoares.lumovie.ui.movie.adapter.concat.UpcomingConcatAdapter


class MovieFragment : Fragment(R.layout.fragment_movie),MovieAdapter.OnMovieSetClickListener {

    private lateinit var binding:FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel> { MovieViewModelFactory(MovieRepositoryImpl(
        RemoteMovieDataSource(RetroFitClient.webservice)
    )) }

    lateinit private var concatAdapter :ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            binding = FragmentMovieBinding.bind(view)

            concatAdapter = ConcatAdapter()

            viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer {
                when(it){
                    is Resource.Loading->{
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Succes->{
                        binding.progressBar.visibility = View.GONE
                        concatAdapter.apply {
                            addAdapter(0,UpcomingConcatAdapter(MovieAdapter(it.data.first.results,this@MovieFragment)))
                            addAdapter(1,TopRatedConcatAdapter(MovieAdapter(it.data.second.results,this@MovieFragment)))
                            addAdapter(2,TopRatedConcatAdapter(MovieAdapter(it.data.third.results,this@MovieFragment)))
                        }
                        binding.rvMovie.adapter = concatAdapter
                    }
                    is Resource.Failure->{
                        binding.progressBar.visibility = View.GONE
                        Log.d("Error","${it.exception}")
                    }

                }


            })
    }

    override fun OnMovieClick(movie: Movie) {
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.original_title,
            movie.original_language,
            movie.release_date
        )
        findNavController().navigate(action)
    }
}