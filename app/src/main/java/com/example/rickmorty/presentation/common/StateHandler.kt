package com.example.rickmorty.presentation.common

import android.view.View
import androidx.core.view.isVisible

fun <T> handleState(
    state: UiState<T>,
    progress: View,
    onSuccess: (T) -> Unit,
    onError: (String) -> Unit = {}
) {
    progress.isVisible = state is UiState.Loading

    when (state) {
        UiState.Loading -> Unit
        is UiState.Success -> onSuccess(state.data)
        is UiState.Error -> onError(state.message)
    }
}
