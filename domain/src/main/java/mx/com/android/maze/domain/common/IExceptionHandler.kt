package mx.com.android.maze.domain.common

fun interface IExceptionHandler {
    fun handle(ex: Exception)
}