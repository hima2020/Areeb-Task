package org.areeb.technicalTask.ui.movies.viewmodel

import android.nfc.tech.MifareUltralight
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import org.areeb.domain.interceptor.GetLocalMoviesUseCase
import org.areeb.domain.interceptor.GetMoviesUseCase
import org.areeb.domain.interceptor.InsertMovieUseCase
import org.areeb.domain.model.MovieDetails
import org.areeb.technicalTask.ui.movies.model.MoviesPagingSource
import org.areeb.technicalTask.utils.GeneralStates
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val insertMovieUseCase: InsertMovieUseCase,
    private val getLocalMoviesUseCase: GetLocalMoviesUseCase


    ) : ViewModel() {
    var movies: List<MovieDetails> = emptyList()
    private val _getMoviesSuccess: MutableSharedFlow<GeneralStates> = MutableSharedFlow()
    val moviesViewState: SharedFlow<GeneralStates> = _getMoviesSuccess

    var filterData = MutableLiveData<String>()
    var jobsFlow = filterData.switchMap {
        Pager(
            config = PagingConfig(
                pageSize = MifareUltralight.PAGE_SIZE,
                enablePlaceholders = false // Adjust as needed
            ),
            pagingSourceFactory = {
                MoviesPagingSource(
                    getMoviesUseCase
                )
            }
        ).liveData
    }


    fun updateFilters(
        filter: String
    ) {
        filterData.value = filter

    }

    fun insertMovie(movie: MovieDetails) {
        viewModelScope.launch {
            insertMovieUseCase.invoke(movie)
        }
    }

    fun getLocalMovies() {
        viewModelScope.launch {
            _getMoviesSuccess.emit(GeneralStates.Loading)
            try {
                val res = getLocalMoviesUseCase.invoke()

                _getMoviesSuccess.emit(GeneralStates.Success(res))


            } catch (ex: Exception) {
                ex.printStackTrace()
                _getMoviesSuccess.emit(
                    GeneralStates.Failed(
                        ex.message ?: "SomeThing went Wrong !!"
                    )
                )
            }
        }
    }
}

