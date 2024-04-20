package com.example.accountsservice.model

import com.example.accountsservice.model.enums.TransactionTypes
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal
import java.util.UUID

@Entity
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
    val transactionType: TransactionTypes,
    val amount: BigDecimal,
    val sourceAccount: UUID,
    val destinationAccount: UUID
)
