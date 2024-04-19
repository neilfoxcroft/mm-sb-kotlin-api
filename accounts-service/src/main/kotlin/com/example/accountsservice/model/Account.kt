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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,
    var balance: BigDecimal? = null,
    var userName: String? = null,
    var password: String? = null
)
