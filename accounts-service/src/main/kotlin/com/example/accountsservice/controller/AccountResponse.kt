package com.example.accountsservice.controller

import java.math.BigDecimal

data class AccountResponse(
    val id: String,
    val balance: BigDecimal,
    val userName: String,
    val password: String
)
