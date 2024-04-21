package com.example.accountsservice.service

import com.example.accountsservice.model.Account
import com.example.accountsservice.repository.AccountRepository
import java.util.Optional
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class AccountService(val accountRepository: AccountRepository) {
    val logger = LoggerFactory.getLogger(this::class.java)

    fun createAccount(account: Account): Account {
        if (!accountRepository.existsByUsername(account.username.toString())) {
            accountRepository.save(account)
            logger.info("💾 Saving new account ${account.username}")
            return account
        } else {
            logger.error("❗Account ${account.username} already exists")
            throw ResponseStatusException(
                HttpStatus.CONFLICT,
                "User with username: {${account.username}} is already taken"
            )
        }
    }

    fun findById(id: Long): Optional<Account> {
        logger.info("🔎 Finding account by id: $id")
        return accountRepository.findById(id)
    }

    fun findAll(): MutableIterable<Account> {
        logger.info("🔎 Find all accounts")
        return accountRepository.findAll()
    }

    fun deleteByUUID(id: Long): Unit {
        logger.info("🗑️ Deleting account by id: $id")
        return accountRepository.deleteById(id)
    }
}