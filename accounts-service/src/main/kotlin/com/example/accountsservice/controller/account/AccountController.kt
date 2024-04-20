package com.example.accountsservice.controller.account

import com.example.accountsservice.model.Account
import com.example.accountsservice.service.AccountService
import java.util.Optional
import org.slf4j.Logger
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
    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping("/register")
    fun registerAccount(@RequestBody accountRequest: Account): Account? =
        accountService.createAccount(accountRequest)

    @GetMapping("/all")
    fun findAll(): MutableIterable<Account> = accountService.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Optional<AccountResponse> =
        accountService.findById(id).map { it.toResponse() } ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND, "Cannot find account"
        )

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<String> {
        return try {
            accountService.deleteByUUID(id)
            ResponseEntity.status(HttpStatus.NO_CONTENT).body("Account deleted successfully.")
        } catch (e: NoSuchElementException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found.")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete account: ${e.message}")
        }
    }

    private fun AccountRequest.toModel(): Account = Account(
        userName = this.userName, password = this.password
    )

    private fun Account.toResponse(): AccountResponse = AccountResponse(
        id = this.id,
        userName = this.userName
    )
}
