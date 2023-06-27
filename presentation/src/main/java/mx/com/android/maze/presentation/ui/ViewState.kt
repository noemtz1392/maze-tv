package mx.com.android.maze.presentation.ui

import mx.com.android.maze.domain.model.Show

sealed class ViewState {

    object Loading : ViewState()

    data class Loaded(val data: List<Show>) : ViewState()

    data class Error(val code: Int, val message: String) : ViewState()

    data class Failure(val exception: Exception?) : ViewState()

    fun isLoading(): Boolean = this is Loading
}
