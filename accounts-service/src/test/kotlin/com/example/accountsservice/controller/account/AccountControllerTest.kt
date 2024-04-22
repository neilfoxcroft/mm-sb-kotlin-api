package com.example.accountsservice.controller.account

import com.example.accountsservice.model.Account
import com.example.accountsservice.service.AccountService
import java.util.Optional
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class AccountControllerTest {

    private val accountService = mock(AccountService::class.java)
    private val accountController = AccountController(accountService)

    @Test
    fun `Given existing accounts, when finding all accounts, then should return list of all account details`() {
        // Given
        val accounts = mutableListOf(
            Account(1L, "user1", "password1"),
            Account(2L, "user2", "password2")
        )
        `when`(accountService.findAll()).thenReturn(accounts)

        // When
        val allAccounts = accountController.findAll()

        // Then
        assertEquals(accounts.size, allAccounts.size)
        assertEquals(accounts[0].id, allAccounts[0].id)
        assertEquals(accounts[1].username, allAccounts[1].username)
        assertEquals(accounts[0].balance, allAccounts[0].balance)
    }

    @Test
    fun `Given an existing account ID, when finding account by ID, then should return account details`() {
        // Given
        val accountId = 1L
        val account = Account(accountId, "test_user", "password")
        `when`(accountService.findById(accountId)).thenReturn(Optional.of(account))

        // When
        val optionalAccount = accountController.findById(accountId)

        // Then
        assertEquals(account.id, optionalAccount.get().id)
        assertEquals(account.username, optionalAccount.get().username)
        assertEquals(account.balance, optionalAccount.get().balance)
    }

    @Test
    fun `Given an existing account ID, when deleting account by ID, then should return success response`() {
        // Given
        val accountId = 1L
        `when`(accountService.deleteByUUID(accountId)).thenReturn(
            ResponseEntity.status(HttpStatus.NO_CONTENT).body("Account deleted successfully.")
        )

        // When
        val response = accountController.deleteById(accountId)

        // Then
        assertEquals(HttpStatus.NO_CONTENT, response.statusCode)
        assertEquals("Account deleted successfully.", response.body)
    }
}
