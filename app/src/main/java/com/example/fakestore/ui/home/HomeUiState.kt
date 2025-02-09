package com.example.fakestore.ui.home

import com.example.fakestore.domain.model.ProductUIModel

data class HomeUiState(
    val list: List<ProductUIModel> = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
)