package com.example.accountsservice.model

import java.math.BigDecimal

data class Account(
    val id: String,
    val balance: BigDecimal,
    val userName: String,
    val password: String
)
