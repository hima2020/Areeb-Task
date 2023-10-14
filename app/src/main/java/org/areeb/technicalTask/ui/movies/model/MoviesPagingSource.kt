package org.areeb.technicalTask.ui.movies.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import org.areeb.domain.interceptor.GetMoviesUseCase
import org.areeb.domain.model.MovieDetails

class MoviesPagingSource(
    private val getMoviesList: GetMoviesUseCase
) : PagingSource<Int, MovieDetails>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDetails> {
        val page = params.key ?: 1
        return try {
            val response = getMoviesList.invoke(
                page
            )

            LoadResult.Page(
                data = response.results ?: emptyList(),
                prevKey = if (page > 1) page - 1 else null,
                nextKey = if (response.results?.isNotEmpty() == true) page + 1 else null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieDetails>): Int? {
        // Handle the case when a new data fetch is needed (e.g., user initiated refresh).
        // This function should return null if there's no key available.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val TAG = "JobPagingSource"
    }
}
