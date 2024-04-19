package com.example.accountsservice.controller.transaction

import com.example.accountsservice.model.Transaction
import com.example.accountsservice.service.TransactionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/transaction")
class TransactionController(
    val transactionService: TransactionService
) {
    @GetMapping("/all")
    fun getAllAccounts(): List<TransactionResponse> =
        transactionService.findAll()
            .map { it.toResponse() }

    private fun Transaction.toResponse(): TransactionResponse =
        TransactionResponse(
            id = this.id,
            transactionType = this.transactionType,
            amount = this.amount,
            sourceAccount = this.sourceAccount,
            destinationAccount = this.destinationAccount,
        )
}
