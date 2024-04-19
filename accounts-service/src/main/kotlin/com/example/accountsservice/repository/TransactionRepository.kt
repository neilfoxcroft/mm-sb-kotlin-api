package com.example.accountsservice.repository

import com.example.accountsservice.model.Transaction
import com.example.accountsservice.model.enums.TransactionTypes
import java.util.UUID
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository

interface TransactionRepository : CrudRepository<Transaction, UUID> {

    fun findTransactionsBySourceAccountIs(sourceAccount: UUID): List<Transaction>
    fun findTransactionsByTransactionType(transactionType: TransactionTypes): List<Transaction>
}