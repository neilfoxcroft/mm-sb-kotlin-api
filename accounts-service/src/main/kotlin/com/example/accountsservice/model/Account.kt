package com.example.accountsservice.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "account")
class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    @Column(name = "username", unique = true)
    var username: String? = null,
    @Column(name = "password")
    var password: String? = null,
    @Column(name = "balance")
    var balance: BigDecimal? = null,
)
