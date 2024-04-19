package com.example.accountsservice.repository

import com.example.accountsservice.model.Account
import java.math.BigDecimal
import java.util.UUID
import org.springframework.stereotype.Repository

@Repository
class AccountRepository {

    private val accounts = listOf(
        Account(id = UUID.randomUUID(), balance = BigDecimal("100.00"), userName = "John Doe", password = "password"),
        Account(
            id = UUID.randomUUID(),
            balance = BigDecimal("10.00"),
            userName = "Mark Boucher",
            password = "password"
        ),
        Account(id = UUID.randomUUID(), balance = BigDecimal("40.40"), userName = "Kevin Hart", password = "password"),
        Account(
            id = UUID.randomUUID(),
            balance = BigDecimal("99.99"),
            userName = "Amber Williams",
            password = "password"
        ),
        Account(
            id = UUID.randomUUID(),
            balance = BigDecimal("1999999.99"),
            userName = "Nicole Jane",
            password = "password"
        ),
    )

    fun findAll(): List<Account> =
        accounts
}