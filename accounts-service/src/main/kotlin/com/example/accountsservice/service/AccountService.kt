package com.example.accountsservice.service

import com.example.accountsservice.model.Account
import com.example.accountsservice.repository.AccountRepository
import org.springframework.stereotype.Service

@Service
class AccountService(val accountRepository: AccountRepository) {

    fun findAll(): List<Account> = accountRepository.findAll()
}