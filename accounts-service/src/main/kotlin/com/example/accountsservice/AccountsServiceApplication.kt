package com.example.accountsservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AccountsServiceApplication

fun main(args: Array<String>) {
    runApplication<AccountsServiceApplication>(*args)
}
