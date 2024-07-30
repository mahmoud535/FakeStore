package com.example.fakestore.ui.basket

import com.example.fakestore.data.local.entity.Basket

data class BasketUiState(
    val list: List<Basket> = emptyList(),
    val isLoading: Boolean = false,
    val totalPrice: Double = 0.0
)