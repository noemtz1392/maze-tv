package mx.com.android.maze.domain.common

sealed class Result<out R> {

    data class Success<out R>(val data: R) : Result<R>()

    data class Failure(val error: Exception? = null) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Failure -> "Failure[error=$error]"
        }
    }

    companion object {
        fun <R> fromNullable(result: R?) = when (result) {
            null -> Failure()
            else -> Success(result)
        }
    }
}