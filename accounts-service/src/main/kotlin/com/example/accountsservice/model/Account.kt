package com.example.accountsservice.model

import java.math.BigDecimal
import java.util.UUID

data class Account(
    val id: UUID,
    val balance: BigDecimal,
    val userName: String,
    val password: String
)
