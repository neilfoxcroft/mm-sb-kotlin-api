package com.example.accountsservice.service

import com.example.accountsservice.controller.transaction.TransactionRequest
import com.example.accountsservice.model.Transaction
import com.example.accountsservice.model.enums.TransactionTypes
import com.example.accountsservice.repository.AccountRepository
import com.example.accountsservice.repository.TransactionRepository
import java.math.BigDecimal
import java.time.LocalDateTime
import org.springframework.stereotype.Service

@Service
class TransactionService(val transactionRepository: TransactionRepository, val accountRepository: AccountRepository) {

    fun findAll(): List<Transaction> = transactionRepository.findAll()

    fun transfer(transactionRequest: TransactionRequest): Transaction {
        var sourceAccount = accountRepository.findById(transactionRequest.sourceAccount).get()
        var destinationAccount = accountRepository.findById(transactionRequest.destinationAccount).get()
        sourceAccount.balance = sourceAccount.balance?.minus(transactionRequest.amount)
        destinationAccount.balance = destinationAccount.balance?.plus(transactionRequest.amount)
        accountRepository.save(sourceAccount)
        accountRepository.save(destinationAccount)
        val transaction = Transaction(
            transactionType = TransactionTypes.TRANSFER,
            amount = transactionRequest.amount,
            date = LocalDateTime.now(),
            sourceAccount = transactionRequest.sourceAccount,
            destinationAccount = transactionRequest.destinationAccount
        )
        return transactionRepository.save(transaction)
    }

    fun deposit(accountId: Long, amount: BigDecimal): Transaction {
        var accountToUpdate = accountRepository.findById(accountId).get()
        accountToUpdate.balance = accountToUpdate.balance?.plus(amount)
        accountRepository.save(accountToUpdate)
        val transaction = Transaction(
            transactionType = TransactionTypes.DEPOSIT,
            amount = amount,
            date = LocalDateTime.now(),
            sourceAccount = accountToUpdate.id,
            destinationAccount = accountToUpdate.id
        )
        transactionRepository.save(transaction)
        return transaction
    }

}