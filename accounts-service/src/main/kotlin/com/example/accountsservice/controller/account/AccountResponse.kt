package com.example.accountsservice.controller.account

import java.math.BigDecimal

data class AccountResponse(
    val id: Long,
    val username: String,
    val balance: BigDecimal?
)
