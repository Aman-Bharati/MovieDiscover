package com.example.moviediscoveryapp.ui.home

import androidx.lifecycle.ViewModel
import com.example.moviediscoveryapp.data.repository.MovieRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    private val compositeDisposable = CompositeDisposable()

    // 🔒 Prevents repeated API calls (ANR FIX)
    private var hasLoaded = false

    fun loadMoviesAndTv(apiKey: String) {

        if (hasLoaded) return
        hasLoaded = true

        _uiState.value = HomeUiState.Loading

        val disposable = repository.fetchMoviesAndTvShows(apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    _uiState.value = HomeUiState.Success(
                        movies = result.first,
                        tvShows = result.second
                    )
                },
                { error ->
                    _uiState.value = HomeUiState.Error(
                        error.localizedMessage ?: "Something went wrong"
                    )
                }
            )

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
