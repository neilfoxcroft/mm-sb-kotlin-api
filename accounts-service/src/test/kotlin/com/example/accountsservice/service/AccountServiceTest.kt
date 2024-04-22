package com.example.accountsservice.service

import com.example.accountsservice.model.Account
import com.example.accountsservice.repository.AccountRepository
import java.util.Optional
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

@ExtendWith(MockitoExtension::class)
class AccountServiceTest {

    @Mock
    private lateinit var accountRepository: AccountRepository

    @InjectMocks
    private lateinit var accountService: AccountService

    private lateinit var testAccount: Account

    @BeforeEach
    fun setUp() {
        testAccount = Account(1L, "test_user", "password")
    }

    @Test
    fun `Given non-existing username, when creating account, then account should be created successfully`() {
        // Given
        `when`(accountRepository.existsByUsername("test_user")).thenReturn(false)
        `when`(accountRepository.save(testAccount)).thenReturn(testAccount)

        // When
        val createdAccount = accountService.createAccount(testAccount)

        // Then
        Assertions.assertEquals(testAccount, createdAccount)
        verify(accountRepository).existsByUsername("test_user")
        verify(accountRepository).save(testAccount)
    }

    @Test
    fun `Given existing username, when creating account, then should throw ResponseStatusException`() {
        // Given
        `when`(accountRepository.existsByUsername("test_user")).thenReturn(true)

        // When-Then
        Assertions.assertThrows(ResponseStatusException::class.java) {
            accountService.createAccount(testAccount)
        }

        verify(accountRepository).existsByUsername("test_user")
    }

    @Test
    fun `Given existing account ID, when finding by ID, then should return the account`() {
        // Given
        `when`(accountRepository.findById(1L)).thenReturn(Optional.of(testAccount))

        // When
        val foundAccount = accountService.findById(1L)

        // Then
        Assertions.assertEquals(testAccount, foundAccount.get())
        verify(accountRepository).findById(1L)
    }

    @Test
    fun `Given non-existing account ID, when finding by ID, then should throw ResponseStatusException`() {
        // Given
        `when`(accountRepository.findById(1L)).thenReturn(Optional.empty())

        // When-Then
        Assertions.assertThrows(ResponseStatusException::class.java) {
            accountService.findById(1L)
        }

        verify(accountRepository).findById(1L)
    }

    @Test
    fun `Given existing username, when finding by username, then should return the account`() {
        // Given
        `when`(accountRepository.findByUsername("test_user")).thenReturn(Optional.of(testAccount))

        // When
        val foundAccount = accountService.findByUsername("test_user")

        // Then
        Assertions.assertEquals(testAccount, foundAccount.get())
        verify(accountRepository).findByUsername("test_user")
    }

    @Test
    fun `Given non-existing username, when finding by username, then should return empty`() {
        // Given
        `when`(accountRepository.findByUsername("test_user")).thenReturn(Optional.empty())

        // When
        val foundAccount = accountService.findByUsername("test_user")

        // Then
        Assertions.assertTrue(foundAccount.isEmpty)
        verify(accountRepository).findByUsername("test_user")
    }

    @Test
    fun `Given accounts exist, when finding all, then should return all accounts`() {
        // Given
        val accounts = listOf(testAccount)
        `when`(accountRepository.findAll()).thenReturn(accounts)

        // When
        val foundAccounts = accountService.findAll()

        // Then
        Assertions.assertEquals(accounts, foundAccounts.toList())
        verify(accountRepository).findAll()
    }

    @Test
    fun `Given existing account ID, when deleting by ID, then should return success response`() {
        // When
        val response = accountService.deleteByUUID(1L)

        // Then
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.statusCode)
        Assertions.assertEquals("Account deleted successfully.", response.body)
        verify(accountRepository).deleteById(1L)
    }

    @Test
    fun `Given non-existing account ID, when deleting by ID, then should return not found response`() {
        // Given
        `when`(accountRepository.deleteById(1L)).thenThrow(NoSuchElementException())

        // When
        val response = accountService.deleteByUUID(1L)

        // Then
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
        Assertions.assertEquals("Account not found.", response.body)
        verify(accountRepository).deleteById(1L)
    }

    @Test
    fun `Given error during deletion, when deleting by ID, then should return internal server error response`() {
        // Given
        `when`(accountRepository.deleteById(1L)).thenThrow(RuntimeException())

        // When
        val response = accountService.deleteByUUID(1L)

        // Then
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.statusCode)
        verify(accountRepository).deleteById(1L)
    }
}
