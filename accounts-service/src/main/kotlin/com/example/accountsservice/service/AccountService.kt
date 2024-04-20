package com.example.accountsservice.service

import com.example.accountsservice.model.Account
import com.example.accountsservice.repository.AccountRepository
import java.util.Optional
import org.springframework.stereotype.Service

@Service
class AccountService(val accountRepository: AccountRepository) {

    fun createAccount(account: Account): Account? {
        val found = accountRepository.findByUserName(account.userName)

        return if (found == null) {
            accountRepository.save(account)
            account
        } else null
    }

    fun findById(id: Long): Optional<Account> =
        accountRepository.findById(id)

    fun findAll(): MutableIterable<Account> =
        accountRepository.findAll()

    fun deleteByUUID(id: Long): Unit =
        accountRepository.deleteById(id)


}