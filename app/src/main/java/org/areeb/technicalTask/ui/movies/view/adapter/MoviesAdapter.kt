package org.areeb.technicalTask.ui.movies.view.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.areeb.domain.model.MovieDetails
import org.areeb.technicalTask.databinding.ItemMovieBinding


class MoviesAdapter(private val onItemClick: (MovieDetails) -> Unit) :
    PagingDataAdapter<MovieDetails, MoviesAdapter.JobViewHolder>(JobDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        getItem(position)?.let { Movie ->
            holder.bind(Movie)
        }
    }


    inner class JobViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(movie: MovieDetails) {

            binding.apply {
                Log.d("IMagePathPoster", "$ImageBaseUrl${movie.poster_path}")
                Glide.with(root.context).load("$ImageBaseUrl${movie.poster_path}").into(ivPoster)
                tvMovieTitle.text = movie.title
                tvMovieOverview.text = movie.overview
                root.setOnClickListener {
                    onItemClick.invoke(movie)
                }
            }
        }

    }

    fun addLocalData(paging: PagingData<MovieDetails>, lifeCycle: Lifecycle) {
        submitData(lifeCycle, paging)
    }

    companion object {
        private val JobDiffCallback = object : DiffUtil.ItemCallback<MovieDetails>() {
            override fun areItemsTheSame(oldItem: MovieDetails, newItem: MovieDetails): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieDetails, newItem: MovieDetails): Boolean {
                return oldItem == newItem
            }
        }

        private const val ImageBaseUrl = "https://image.tmdb.org/t/p/original"
    }
}