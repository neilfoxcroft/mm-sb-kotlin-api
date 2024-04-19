package com.example.accountsservice.service

import com.example.accountsservice.model.Account
import com.example.accountsservice.repository.AccountRepository
import org.springframework.stereotype.Service

@Service
class AccountService(val accountRepository: AccountRepository) {

    fun findAll(): MutableIterable<Account> = accountRepository.findAll()
    fun create(account: Account): Account = accountRepository.save(account)
}