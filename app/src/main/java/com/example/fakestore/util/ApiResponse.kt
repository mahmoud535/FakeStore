package com.example.fakestore.util

sealed class ApiResponse<out T> {
    data class Success<TYPE>(val body: TYPE) : ApiResponse<TYPE>()

    data class Error(val errorMessage: String) : ApiResponse<Nothing>()
}
