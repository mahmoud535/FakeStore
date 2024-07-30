package com.example.fakestore.domain.validation.payment

import com.example.fakestore.domain.validation.ValidateEmptyField

data class PaymentValidations(
    val validateCardNumber: ValidateCardNumber,
    val validateCardHolderName: ValidateEmptyField,
    val validateExpiryDate: ValidateExpiryDate,
    val validateCvc: ValidateCvc
)