package com.example.accountsservice.model

import com.example.accountsservice.model.enums.TransactionTypes
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "transaction")
class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
    @Column(name = "transaction_type")
    var transactionType: TransactionTypes? = null,
    @Column(name = "amount")
    var amount: BigDecimal? = null,
    @Column(name = "transaction_date")
    var date: LocalDateTime? = null,
    @Column(name = "source_account")
    var sourceAccount: Long? = null,
    @Column(name = "destination_account")
    var destinationAccount: Long? = null,
)
