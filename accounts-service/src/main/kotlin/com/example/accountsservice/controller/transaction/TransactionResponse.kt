package com.example.accountsservice.controller.transaction

import com.example.accountsservice.model.enums.TransactionTypes
import java.math.BigDecimal
import java.util.UUID

data class TransactionResponse(
    val id: Long,
    val transactionType: TransactionTypes?,
    val amount: BigDecimal?,
    val sourceAccount: UUID?,
    val destinationAccount: UUID?
)
