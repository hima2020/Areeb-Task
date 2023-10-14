package org.areeb.technicalTask.ui.details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import org.areeb.domain.model.MovieDetails
import org.areeb.technicalTask.databinding.FragmentMovieDetailsBinding

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        initMovie(Gson().fromJson(arguments?.get(argKey).toString(), MovieDetails::class.java))
        return binding.root
    }

    private fun initMovie(movie: MovieDetails) {
        binding.apply {
            Glide.with(requireContext()).load("${ImageBaseUrl}${movie.poster_path}")
                .into(ivMoviePoster)
            tvMovieTitle.text = movie.title
            tvMovieOverview.text = movie.overview
            tvMovieDate.text = movie.release_date
            ivBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }


    }


    companion object {
        private const val argKey = "MovieData"
        private const val ImageBaseUrl = "https://image.tmdb.org/t/p/original"
    }


}