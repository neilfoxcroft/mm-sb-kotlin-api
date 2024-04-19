package com.example.accountsservice.model

import com.example.accountsservice.model.enums.TransactionTypes
import java.math.BigDecimal
import java.util.UUID

data class Transaction(
    val id: UUID,
    val transactionType: TransactionTypes,
    val amount: BigDecimal,
    val sourceAccount: UUID,
    val destinationAccount: UUID
)
