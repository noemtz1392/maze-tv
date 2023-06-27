package mx.com.android.maze.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.com.android.maze.domain.common.Result
import mx.com.android.maze.domain.interactor.FindTvShowByQueryUseCase
import mx.com.android.maze.domain.interactor.GetCastFromTvShowUseCase
import mx.com.android.maze.domain.interactor.GetTvShowByIdUseCase
import mx.com.android.maze.domain.interactor.GetTvShowsUseCase
import mx.com.android.maze.domain.model.Show
import mx.com.android.maze.presentation.utils.Commons
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val getTvShowsUseCase: GetTvShowsUseCase,
    private val findTvShowByQueryUseCase: FindTvShowByQueryUseCase,
    private val getTvShowByIdUseCase: GetTvShowByIdUseCase,
    private val getCastFromTvShowUseCase: GetCastFromTvShowUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loaded(emptyList()))
    val viewState = _viewState.asStateFlow()

    private val _findViewState = MutableStateFlow<ViewState>(ViewState.Loaded(emptyList()))
    val findViewState = _findViewState.asStateFlow()

    private val _castViewState = MutableStateFlow<CastViewState>(CastViewState.Loaded(emptyList()))
    val castViewState = _castViewState.asStateFlow()

    private val _tvShowState = MutableLiveData<Show?>()
    val tvShowState: LiveData<Show?> = _tvShowState

    val isLoading: LiveData<Boolean> = Transformations.map(_viewState.asLiveData()) {
        it.isLoading()
    }

    fun getShowsTV() {
        viewModelScope.launch {
            _viewState.value = ViewState.Loading
            when (val result = getTvShowsUseCase(Commons.getCurrentDate())) {
                is Result.Failure -> _viewState.value = ViewState.Failure(result.error)
                is Result.Success -> _viewState.value = ViewState.Loaded(result.data)
            }
        }
    }

    fun findTvShowByQuery(query: String) {
        viewModelScope.launch {
            when (val useCase = findTvShowByQueryUseCase(query)) {
                is Result.Failure -> _findViewState.value = ViewState.Failure(useCase.error)
                is Result.Success -> _findViewState.value = ViewState.Loaded(useCase.data)
            }
        }
    }

    fun getTvShowById(showId: String) {
        viewModelScope.launch {
            when (val useCase = getTvShowByIdUseCase(showId)) {
                is Result.Failure -> {}
                is Result.Success -> _tvShowState.value = useCase.data
            }
        }
    }

    fun getCastFromTvShow(showId: String) {
        viewModelScope.launch {
            _castViewState.value = CastViewState.Loading
            when (val useCase = getCastFromTvShowUseCase(showId)) {
                is Result.Failure -> _castViewState.value = CastViewState.Failure(useCase.error)
                is Result.Success -> _castViewState.value = CastViewState.Loaded(useCase.data)
            }
        }
    }
}