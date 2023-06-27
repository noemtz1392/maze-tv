package mx.com.android.maze.domain.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class CoroutineUseCase<in P, out R>(
    private val handler: IExceptionHandler,
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(param: P): Result<R> = try {
        withContext(dispatcher) {
            execute(param)
        }
    } catch (ex: Exception) {
        handler.handle(ex)
        Result.Failure(ex)
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P): Result<R>
}