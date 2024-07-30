package com.example.fakestore.mapper

import com.example.fakestore.data.model.ProductDto
import com.example.fakestore.domain.model.ProductUIModel

fun ProductDto.toProductUIModel() = ProductUIModel(
    id = id ?: 0,
    title = title ?: "",
    price = price ?: 0.0,
    imageUrl = imageUrl ?: "",
    rating = rating?.toRatingUIModel(),
    category = category ?: ""
)

fun List<ProductDto>.toProductUIModel() = map(ProductDto::toProductUIModel)