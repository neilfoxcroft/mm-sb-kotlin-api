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
    // TODO Handle nulls better
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,
    val transactionType: TransactionTypes? = null,
    val amount: BigDecimal? = null,
    val sourceAccount: UUID? = null,
    val destinationAccount: UUID? = null
)
