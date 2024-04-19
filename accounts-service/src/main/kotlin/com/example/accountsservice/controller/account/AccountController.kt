package com.example.accountsservice.controller.account

import com.example.accountsservice.model.Account
import com.example.accountsservice.service.AccountService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/account")
class AccountController(val accountService: AccountService) {

    @GetMapping("/all")
    fun getAllAccounts(): List<AccountResponse> =
        accountService.findAll()
            .map { it.toResponse() }

    private fun Account.toResponse(): AccountResponse =
        AccountResponse(
            id = this.id,
            balance = this.balance,
            userName = this.userName,
            password = this.password
        )
}
