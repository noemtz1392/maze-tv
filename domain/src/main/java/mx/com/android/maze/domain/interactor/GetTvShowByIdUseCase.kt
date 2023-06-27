package mx.com.android.maze.domain.interactor

import kotlinx.coroutines.CoroutineDispatcher
import mx.com.android.maze.domain.common.CoroutineUseCase
import mx.com.android.maze.domain.common.IExceptionHandler
import mx.com.android.maze.domain.common.Result
import mx.com.android.maze.domain.di.IoDispatcher
import mx.com.android.maze.domain.model.Show
import mx.com.android.maze.domain.repository.TvShowRepository

class GetTvShowByIdUseCase(
    private val tvShowRepository: TvShowRepository,
    handler: IExceptionHandler,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CoroutineUseCase<String, Show>(handler, dispatcher) {
    override suspend fun execute(parameters: String): Result<Show> {
        return Result.fromNullable(tvShowRepository.getTvShowById(showId = parameters))
    }
}