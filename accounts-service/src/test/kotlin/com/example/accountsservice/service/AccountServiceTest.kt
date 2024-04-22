package com.example.accountsservice.service

import com.example.accountsservice.model.Account
import com.example.accountsservice.repository.AccountRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.server.ResponseStatusException

@ExtendWith(MockitoExtension::class)
class AccountServiceTest {

    private val accountRepository = mock(AccountRepository::class.java)
    private val passwordEncoder = mock(PasswordEncoder::class.java)
    private val authenticationService = mock(AuthenticationService::class.java)
    private val accountService = AccountService(accountRepository, authenticationService)

    @Test
    fun `createAccount should return saved account when account does not exist`() {
        // Given
        val account = Account(0, "username", "password")
        `when`(accountRepository.existsByUsername(account.username.toString())).thenReturn(false)
        `when`(passwordEncoder.encode(account.password)).thenReturn("hashedPassword")
        `when`(accountRepository.save(account)).thenReturn(account)

        // When
        val result = accountService.createAccount(account)

        // Then
        assertEquals(account, result)
    }

    @Test
    fun `createAccount should throw exception when account already exists`() {
        // Given
        val account = Account(0, "username", "password")
        `when`(accountRepository.existsByUsername(account.username.toString())).thenReturn(true)

        // When & Then
        val exception = assertThrows(ResponseStatusException::class.java) {
            accountService.createAccount(account)
        }

        assertEquals(HttpStatus.CONFLICT, exception.statusCode)
    }
}
