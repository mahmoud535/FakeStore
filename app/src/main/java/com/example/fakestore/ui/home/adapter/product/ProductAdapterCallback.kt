package com.example.fakestore.ui.home.adapter.product

import com.example.fakestore.domain.model.ProductUIModel

interface ProductAdapterCallback {
    fun onClickCard(product: ProductUIModel)
    fun onClickAddToBagButton(product: ProductUIModel)
}