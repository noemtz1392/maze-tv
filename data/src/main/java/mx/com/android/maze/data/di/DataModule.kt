package mx.com.android.maze.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.com.android.maze.data.mappers.ApiCastToPersonMapper
import mx.com.android.maze.data.mappers.ApiShowToShowMapper
import mx.com.android.maze.data.repository.ShowTvDataSource
import mx.com.android.maze.data.repository.TvDataShowRepository
import mx.com.android.maze.data.source.remote.MazeApi
import mx.com.android.maze.data.source.remote.RetrofitTvShowDataSource
import mx.com.android.maze.domain.repository.TvShowRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideShowTvRepository(showTvDataSource: ShowTvDataSource): TvShowRepository =
        TvDataShowRepository(showTvDataSource)

    @Singleton
    @Provides
    fun provideShowTvDataSource(
        mazeApi: MazeApi,
        apiCastToPersonMapper: ApiCastToPersonMapper,
        apiShowToShowMapper: ApiShowToShowMapper
    ): ShowTvDataSource =
        RetrofitTvShowDataSource(mazeApi, apiCastToPersonMapper, apiShowToShowMapper)
}