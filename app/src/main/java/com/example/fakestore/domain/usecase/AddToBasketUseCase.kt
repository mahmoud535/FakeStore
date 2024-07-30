package com.example.fakestore.domain.usecase

import com.example.fakestore.data.local.entity.Basket
import com.example.fakestore.data.repository.BasketRepository
import javax.inject.Inject

class AddToBasketUseCase @Inject constructor(
    private val basketRepository: BasketRepository
) {
    suspend operator fun invoke(basket: Basket) = basketRepository.addProduct(basket)
}