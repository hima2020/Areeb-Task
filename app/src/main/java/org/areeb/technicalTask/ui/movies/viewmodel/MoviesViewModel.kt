package org.areeb.technicalTask.ui.movies.viewmodel

import android.nfc.tech.MifareUltralight
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.areeb.domain.interceptor.GetMoviesUseCase
import org.areeb.domain.model.MovieDetails
import org.areeb.technicalTask.ui.movies.model.MoviesPagingSource
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,

    ) : ViewModel() {
    var movies: List<MovieDetails> = emptyList()
//    private val _getMoviesSuccess: MutableSharedFlow<GeneralStates> = MutableSharedFlow()
//    val successResponse: SharedFlow<GeneralStates> = _getMoviesSuccess

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
}

