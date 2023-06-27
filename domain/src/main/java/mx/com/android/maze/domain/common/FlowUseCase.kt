package mx.com.android.maze.domain.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in P, out R>(
    private val handler: IExceptionHandler,
    private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(parameters: P) = execute(parameters).catch { e ->
        handler.handle(e as Exception)
        emit(Result.Failure(e))
    }.flowOn(dispatcher)

    protected abstract fun execute(parameters: P): Flow<Result<R>>

}