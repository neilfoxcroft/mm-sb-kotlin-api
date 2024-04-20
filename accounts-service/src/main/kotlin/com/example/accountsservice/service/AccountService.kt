package com.example.accountsservice.service

import com.example.accountsservice.model.Account
import com.example.accountsservice.repository.AccountRepository
import java.util.Optional
import org.springframework.stereotype.Service

@Service
class AccountService(val accountRepository: AccountRepository) {

    fun createAccount(account: Account): Account = accountRepository.save(account)

    fun findById(id: Long): Optional<Account> = accountRepository.findById(id)

    fun findAll(): MutableIterable<Account> = accountRepository.findAll()

    fun deleteByUUID(id: Long): Unit = accountRepository.deleteById(id)
}