package com.lucassoares.lumovie.ui.movie.adapter.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucassoares.lumovie.core.BaseConcatHolder
import com.lucassoares.lumovie.databinding.PopularMovieBinding
import com.lucassoares.lumovie.databinding.UpcomingMovieRowBinding
import com.lucassoares.lumovie.ui.movie.adapter.MovieAdapter

class UpcomingConcatAdapter(private val movieAdapter: MovieAdapter):
    RecyclerView.Adapter<BaseConcatHolder<*>>()

{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding = UpcomingMovieRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when(holder){
            is ConcatViewHolder -> holder.bind(movieAdapter)
        }
    }

    override fun getItemCount(): Int = 1



    private inner class ConcatViewHolder ( val binding: UpcomingMovieRowBinding) : BaseConcatHolder<MovieAdapter>(binding.root){
        override fun bind(adapter: MovieAdapter) {
            binding.rvUpcomingMovie.adapter = adapter
        }
    }
}