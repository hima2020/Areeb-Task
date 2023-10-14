package org.areeb.technicalTask.ui.movies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.areeb.technicalTask.databinding.FragmentMoviesBinding
import org.areeb.technicalTask.ui.movies.view.adapter.MoviesAdapter
import org.areeb.technicalTask.ui.movies.view.adapter.PagingLoadStateAdapter
import org.areeb.technicalTask.ui.movies.viewmodel.MoviesViewModel
import org.areeb.technicalTask.utils.Utils

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var adapter: MoviesAdapter
    private val viewModel: MoviesViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        initObservation()
        initActions()
        return binding.root
    }

    private fun initObservation() {

        viewModel.jobsFlow.observe(viewLifecycleOwner) { pagingData ->
            adapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
        }

    }

    private fun initActions() {
        viewModel.updateFilters("")

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
        }
        adapter = MoviesAdapter { movie ->
            findNavController().navigate(
                MoviesFragmentDirections.actionMoviesFragmenttoMovieDetailsFragment(
                    Gson().toJson(movie)
                )
            )
        }

        lifecycleScope.launch {
            adapter.loadStateFlow.collect {
                if (it.source.refresh == LoadState.Loading) {
                    Utils.showProgressBar(requireContext())
                } else {
                    Utils.hideProgressBar()
                }
            }
        }
        binding.apply {
            rvUsers.adapter =
                adapter.withLoadStateFooter(PagingLoadStateAdapter(adapter::retry))
        }


    }



}