package android.example.firstapp.DI

import android.example.firstapp.Model.Network.Model.MovieDtoMapper
import android.example.firstapp.Model.Network.MovieService
import android.example.firstapp.Repository.MovieRepository
import android.example.firstapp.Repository.MovieRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMovieRepository(
        MovieService: MovieService,
        MovieDtoMapper: MovieDtoMapper
    ): MovieRepository {
        return MovieRepository_Impl(MovieService, MovieDtoMapper)
    }
}