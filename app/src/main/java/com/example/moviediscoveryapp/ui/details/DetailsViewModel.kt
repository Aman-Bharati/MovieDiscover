package com.example.moviediscoveryapp.ui.details

import androidx.lifecycle.ViewModel
import com.example.moviediscoveryapp.data.repository.MovieRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailsViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val uiState: StateFlow<DetailsUiState> = _uiState

    private val compositeDisposable = CompositeDisposable()

    fun loadDetails(id: Int, apiKey: String) {
        _uiState.value = DetailsUiState.Loading

        val disposable = repository.fetchTitleDetails(id, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    _uiState.value = DetailsUiState.Success(result)
                },
                { error ->
                    _uiState.value = DetailsUiState.Error(
                        error.localizedMessage ?: "Failed to load details"
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
