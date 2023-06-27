package mx.com.android.maze.presentation.ui

import mx.com.android.maze.domain.model.Person

sealed class CastViewState {

    object Loading : CastViewState()

    data class Loaded(val data: List<Person>) : CastViewState()

    data class Failure(val exception: Exception?) : CastViewState()

    fun isLoading(): Boolean = this is Loading
}
