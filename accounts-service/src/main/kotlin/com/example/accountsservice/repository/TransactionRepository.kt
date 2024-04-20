package com.example.accountsservice.repository

import com.example.accountsservice.model.Transaction
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository

interface TransactionRepository : CrudRepository<Transaction, Long>