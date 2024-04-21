package com.example.accountsservice.controller.transaction

import java.math.BigDecimal

data class TransactionRequest(
    val amount: BigDecimal,
    val currency: String,
    val sourceAccount: Long,
    val destinationAccount: Long
)
