package com.example.fakestore.domain.validation

data class ValidationResult(
    val isSuccessful: Boolean,
    val message: String = "",
)
