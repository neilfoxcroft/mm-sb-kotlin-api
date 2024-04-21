package com.example.accountsservice.repository

import com.example.accountsservice.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository

interface TransactionRepository : JpaRepository<Transaction, Long>