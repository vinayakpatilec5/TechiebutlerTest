package com.example.techiebutlertest.di

import com.example.techiebutlertest.data.remote.PostDataApi
import com.example.techiebutlertest.data.repository.HomeRepositoryImpl
import com.example.techiebutlertest.data.repository.PostDataRemoteSource
import com.example.techiebutlertest.data.repository.PostDataSource
import com.example.techiebutlertest.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DataModule {

    @Provides
    @ViewModelScoped
    fun provideRemoteDataSource(
        api: PostDataApi
    ): PostDataSource.Remote {
        return PostDataRemoteSource(api)
    }

    @Provides
    @ViewModelScoped
    fun provideMovieRepository(dataSource: PostDataSource.Remote): HomeRepository {
        return HomeRepositoryImpl(dataSource)
    }
}