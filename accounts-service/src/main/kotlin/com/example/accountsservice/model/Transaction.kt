package com.example.accountsservice.model

import com.example.accountsservice.model.enums.TransactionTypes
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.util.UUID

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
    @Column(name = "source_account")
    var sourceAccount: UUID? = null,
    @Column(name = "destination_account")
    var destinationAccount: UUID? = null,
)
