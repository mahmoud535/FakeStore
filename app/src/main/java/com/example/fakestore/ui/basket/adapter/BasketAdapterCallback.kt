package com.example.fakestore.ui.basket.adapter

import com.example.fakestore.data.local.entity.Basket

interface BasketAdapterCallback {

    fun onAddClickListener(basket: Basket)
    fun onRemoveClickListener(basket: Basket)
}