package com.example.accountsservice.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal
import java.util.UUID

@Entity
data class Account(
    // TODO Handle nulls better
    // Still struggling with Nulls in Kotlin
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,
    var balance: BigDecimal = BigDecimal.ZERO,
    var userName: String = "NO USERNAME",
    var password: String = "NO PASSWORD",
)
