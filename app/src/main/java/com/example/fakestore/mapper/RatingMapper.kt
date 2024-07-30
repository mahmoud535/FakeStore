package com.example.fakestore.mapper

import com.example.fakestore.data.model.RatingDto
import com.example.fakestore.domain.model.RatingUIModel

fun RatingDto.toRatingUIModel() = RatingUIModel(
    rate = rate,
    count = count
)