package com.example.accountsservice.controller.account

import com.example.accountsservice.model.Account
import com.example.accountsservice.service.AccountService
import java.math.BigDecimal
import java.util.Optional
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/account")
class AccountController(val accountService: AccountService) {
    val logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping("/register")
    fun registerAccount(@RequestBody accountRequest: AccountRequest): AccountResponse {
        return accountService.createAccount(accountRequest.toModel()).toResponse()
    }

    @PostMapping("/login")
    fun loginAccount(@RequestBody accountRequest: AccountRequest): String {
        return accountService.loginAccount(accountRequest.toModel())
    }

    @GetMapping("/all")
    fun findAll(): List<AccountResponse> {
        return accountService.findAll().map { it.toResponse() }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Optional<AccountResponse> {
        return accountService.findById(id).map { it.toResponse() } ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND, "Cannot find account"
        )
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<String> {
        return accountService.deleteByUUID(id)
    }

    private fun AccountRequest.toModel(): Account = Account(
        id = 0, balance = BigDecimal.ZERO, username = this.username, password = this.password
    )

    private fun Account.toResponse(): AccountResponse = AccountResponse(
        id = this.id,
        username = this.username.toString(),
        balance = this.balance
    )
}
