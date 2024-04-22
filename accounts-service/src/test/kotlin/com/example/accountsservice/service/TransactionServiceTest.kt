package com.example.accountsservice.service

import com.example.accountsservice.controller.transaction.TransactionRequest
import com.example.accountsservice.model.Account
import com.example.accountsservice.model.enums.TransactionTypes
import com.example.accountsservice.repository.AccountRepository
import com.example.accountsservice.repository.TransactionRepository
import java.math.BigDecimal
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class TransactionServiceTest {

    @Mock
    private lateinit var transactionRepository: TransactionRepository

    @Mock
    private lateinit var accountRepository: AccountRepository

    @InjectMocks
    private lateinit var transactionService: TransactionService

    private lateinit var transactionRequest: TransactionRequest

    @BeforeEach
    fun setUp() {
        transactionRequest = TransactionRequest(BigDecimal.valueOf(100), "R", 1L, 2L)
    }

    @Test
    fun `should deposit amount to the specified account`() {
        // given
        val account = mockAccount(1L, BigDecimal.valueOf(500))
        `when`(accountRepository.findById(1L)).thenReturn(java.util.Optional.of(account))

        // when
        val transaction = transactionService.deposit(1L, BigDecimal.valueOf(200))

        // then
        assertEquals(TransactionTypes.DEPOSIT, transaction.transactionType)
        assertEquals(BigDecimal.valueOf(700), account.balance)
    }

    private fun mockAccount(id: Long, balance: BigDecimal): Account {
        return Account(id, "user_$id", "password", balance)
    }
}
