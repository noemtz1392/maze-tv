package mx.com.android.maze.domain.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import mx.com.android.maze.domain.common.IExceptionHandler
import mx.com.android.maze.domain.interactor.FindTvShowByQueryUseCase
import mx.com.android.maze.domain.interactor.GetCastFromTvShowUseCase
import mx.com.android.maze.domain.interactor.GetTvShowByIdUseCase
import mx.com.android.maze.domain.interactor.GetTvShowsUseCase
import mx.com.android.maze.domain.repository.TvShowRepository

@Module
object UseCaseModule {

    @Provides
    fun provideGetShowTvUseCase(
        tvShowRepository: TvShowRepository,
        handler: IExceptionHandler,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ) = GetTvShowsUseCase(tvShowRepository, handler, dispatcher)

    @Provides
    fun provideFindTvShowByQuery(
        tvShowRepository: TvShowRepository,
        handler: IExceptionHandler,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ) = FindTvShowByQueryUseCase(tvShowRepository, handler, dispatcher)

    @Provides
    fun provideGetTvShowById(
        tvShowRepository: TvShowRepository,
        handler: IExceptionHandler,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ) = GetTvShowByIdUseCase(tvShowRepository, handler, dispatcher)

    @Provides
    fun provideGetCastFromTvShow(
        tvShowRepository: TvShowRepository,
        handler: IExceptionHandler,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ) = GetCastFromTvShowUseCase(tvShowRepository, handler, dispatcher)
}