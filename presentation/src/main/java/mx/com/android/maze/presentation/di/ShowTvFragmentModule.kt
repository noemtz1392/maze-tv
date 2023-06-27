package mx.com.android.maze.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import mx.com.android.maze.domain.interactor.FindTvShowByQueryUseCase
import mx.com.android.maze.domain.interactor.GetCastFromTvShowUseCase
import mx.com.android.maze.domain.interactor.GetTvShowByIdUseCase
import mx.com.android.maze.domain.interactor.GetTvShowsUseCase
import mx.com.android.maze.presentation.ui.MainViewModel

@Module
@InstallIn(ViewModelComponent::class)
object ShowTvFragmentModule {

    @Provides
    @ViewModelScoped
    fun bindsMainViewModel(
        getTvShowsUseCase: GetTvShowsUseCase,
        findTvShowByQueryUseCase: FindTvShowByQueryUseCase,
        getTvShowByIdUseCase: GetTvShowByIdUseCase,
        getCastFromTvShowUseCase: GetCastFromTvShowUseCase
    ) = MainViewModel(
        getTvShowsUseCase = getTvShowsUseCase,
        findTvShowByQueryUseCase = findTvShowByQueryUseCase,
        getTvShowByIdUseCase = getTvShowByIdUseCase,
        getCastFromTvShowUseCase = getCastFromTvShowUseCase
    )
}