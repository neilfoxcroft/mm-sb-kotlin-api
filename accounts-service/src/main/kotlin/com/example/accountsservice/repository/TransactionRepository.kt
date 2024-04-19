package com.example.accountsservice.repository

import com.example.accountsservice.model.Transaction
import com.example.accountsservice.model.enums.TransactionTypes
import java.math.BigDecimal
import java.util.UUID
import org.springframework.stereotype.Repository

@Repository
class TransactionRepository {

    private val transaction = listOf(
        Transaction(
            id = UUID.randomUUID(),
            transactionType = TransactionTypes.DEPOSIT,
            amount = BigDecimal(100),
            sourceAccount = UUID.randomUUID(),
            destinationAccount = UUID.randomUUID()
        ),
        Transaction(
            id = UUID.randomUUID(),
            transactionType = TransactionTypes.DEPOSIT,
            amount = BigDecimal(100),
            sourceAccount = UUID.randomUUID(),
            destinationAccount = UUID.randomUUID()
        ),
        Transaction(
            id = UUID.randomUUID(),
            transactionType = TransactionTypes.TRANSFER,
            amount = BigDecimal(100),
            sourceAccount = UUID.randomUUID(),
            destinationAccount = UUID.randomUUID()
        ),
    )

    fun findAll(): List<Transaction> =
        transaction
}