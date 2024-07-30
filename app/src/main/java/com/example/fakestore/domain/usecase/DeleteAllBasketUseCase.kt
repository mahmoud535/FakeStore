package com.example.fakestore.domain.usecase

import com.example.fakestore.data.repository.BasketRepository
import javax.inject.Inject

class DeleteAllBasketUseCase @Inject constructor(
    private val repository: BasketRepository
) {

    suspend operator fun invoke() = repository.deleteAll()
}