package com.example.fakestore.ui

sealed interface UiEvent {
    data class Success(val successMessage: String = "") : UiEvent

    data class Error(val errorMessage: String) : UiEvent
}