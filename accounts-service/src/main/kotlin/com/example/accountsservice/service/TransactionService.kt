package com.example.accountsservice.service

import com.example.accountsservice.model.Transaction
import com.example.accountsservice.repository.TransactionRepository
import org.springframework.stereotype.Service

@Service
class TransactionService(val transactionRepository: TransactionRepository) {

    fun findAll(): List<Transaction> = transactionRepository.findAll()
}