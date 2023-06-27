package mx.com.android.maze.domain.interactor

import kotlinx.coroutines.CoroutineDispatcher
import mx.com.android.maze.domain.common.CoroutineUseCase
import mx.com.android.maze.domain.common.IExceptionHandler
import mx.com.android.maze.domain.common.Result
import mx.com.android.maze.domain.di.IoDispatcher
import mx.com.android.maze.domain.model.Show
import mx.com.android.maze.domain.repository.TvShowRepository

class GetTvShowsUseCase(
    private val tvShowRepository: TvShowRepository,
    handler: IExceptionHandler,
    @IoDispatcher dispatcher: CoroutineDispatcher
) :CoroutineUseCase<String, List<Show>>(handler, dispatcher) {

    override suspend fun execute(parameters: String): Result<List<Show>> {
        return Result.fromNullable(tvShowRepository.getTvShows(date = parameters))
    }
}