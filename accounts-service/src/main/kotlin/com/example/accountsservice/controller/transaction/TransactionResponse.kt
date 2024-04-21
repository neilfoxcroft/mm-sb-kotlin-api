package com.example.accountsservice.controller.transaction

import java.math.BigDecimal

data class TransactionResponse(
    val id: Long,
    val transactionMessage: String?,
    val amount: BigDecimal?,
    val destinationAccount: Long?
)
