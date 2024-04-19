package com.example.accountsservice.controller.account

import java.math.BigDecimal
import java.util.UUID

data class AccountResponse(
    val id: UUID,
    val balance: BigDecimal,
    val userName: String,
    val password: String
)
