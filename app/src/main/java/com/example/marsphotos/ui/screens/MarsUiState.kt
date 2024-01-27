package com.example.marsphotos.ui.screens

/**
 * A sealed interface representing the state of the Mars UI.
 */
sealed interface MarsUiState {
    /**
     * Represents a successful state with the retrieved Mars photos.
     *
     * @property photos The retrieved Mars photos.
     */
    data class Success(val photos: String) : MarsUiState

    /**
     * Represents an error state.
     */
    object Error : MarsUiState

    /**
     * Represents a loading state.
     */
    object Loading : MarsUiState
}