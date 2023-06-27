package mx.com.android.maze.domain.common

abstract class Mapper<in E, T> {
    abstract fun mapFrom(from: E): T

    fun mapFromOptional(from: E?): T? {
        return if (from != null)
            mapFrom(from)
        else null
    }

    fun mapFromList(from: List<E>): List<T> {
        return from.map { mapFrom(it) }
    }

    fun mapFromListOptional(from: List<E>?): List<T>? {
        return from?.map { mapFrom(it) }
    }
}