package org.areeb.technicalTask.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.areeb.data.repo.RepositoryImp
import org.areeb.data.source.remote.ApiService
import org.areeb.domain.repo.Repository

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {


    @Provides
    fun provideRepoModule(apiService: ApiService): Repository {
        return RepositoryImp(apiService)

    }

}