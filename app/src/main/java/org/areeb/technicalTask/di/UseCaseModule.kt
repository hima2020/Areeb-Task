package org.areeb.technicalTask.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.areeb.domain.interceptor.GetMovieDetailsUseCase
import org.areeb.domain.interceptor.GetMoviesUseCase
import org.areeb.domain.repo.Repository

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetMoviesUseCase(repo: Repository): GetMoviesUseCase {
        return GetMoviesUseCase(repo)
    }

    @Provides
    fun provideGetMovieDetailsUseCase(repo: Repository): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCase(repo)
    }

}