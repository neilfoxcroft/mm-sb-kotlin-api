package com.example.accountsservice.controller.transaction

import com.example.accountsservice.model.Transaction
import com.example.accountsservice.model.enums.TransactionTypes
import com.example.accountsservice.service.TransactionService
import java.time.LocalDateTime
import java.util.Currency
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/transaction")
class TransactionController(
    val transactionService: TransactionService
) {
    @GetMapping("/all")
    fun getAllAccounts(): List<Transaction> =
        transactionService.findAll()

    @PostMapping("/deposit")
    fun depositIntoAccount(@RequestBody transactionRequest: TransactionRequest): TransactionResponse =
        transactionService.deposit(transactionRequest.sourceAccount, transactionRequest.amount)
            .toSuccessResponse()

    @PostMapping("/transfer")
    fun transferIntoAccount(@RequestBody transactionRequest: TransactionRequest): TransactionResponse =
        transactionService.transfer(transactionRequest)
            .toSuccessResponse()

    @GetMapping("/report/{id}")
    fun findRT(@PathVariable id: Long): List<Transaction> {
        return transactionService.findBySourceId(id)
    }

    private fun TransactionRequest.toModel(transactionType: TransactionTypes, currency: Currency): Transaction =
        Transaction(
            amount = this.amount,
            transactionType = transactionType,
            date = LocalDateTime.now(),
            sourceAccount = this.sourceAccount,
            destinationAccount = this.destinationAccount,
        )

    private fun Transaction.toTransactionResponse(): TransactionResponse =
        TransactionResponse(
            id = this.id,
            amount = this.amount,
            transactionMessage = "Transaction Success",
            destinationAccount = this.destinationAccount,
        )

    private fun Transaction.toSuccessResponse(): TransactionResponse =
        TransactionResponse(
            id = this.id,
            amount = this.amount,
            transactionMessage = "Transaction Success",
            destinationAccount = this.destinationAccount,
        )
}
