package com.example.accountsservice.service

import com.example.accountsservice.model.Account
import com.example.accountsservice.repository.AccountRepository
import java.util.Optional
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class AccountService(val accountRepository: AccountRepository) {
    val logger = LoggerFactory.getLogger(this::class.java)
    val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder()
    fun createAccount(account: Account): Account {
        if (!accountRepository.existsByUsername(account.username.toString())) {
            // Create Hashed Password
            val hashedPassword = passwordEncoder.encode(account.password)
            account.password = hashedPassword
            accountRepository.save(account)
            logger.info("💾 Saving new account ${account.username}")
            return account
        } else {
            logger.error("❗Account ${account.username} already exists")
            throw ResponseStatusException(
                HttpStatus.CONFLICT,
                "User with username: ${account.username} is already taken"
            )
        }
    }

    fun findById(id: Long): Optional<Account> {
        logger.info("🔎 Finding account by id: $id")
        var account = accountRepository.findById(id)
        if (account.isPresent) {
            logger.info("🔎 Found account with id: $id")
            return Optional.of(account.get())
        } else {
            logger.info("🔎 No account found with id: $id")
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Account with id: $id is not found"
            )
        }
    }

    fun findByUsername(username: String): Optional<Account> {
        logger.info("🔎 Finding account by username: $username")
        return accountRepository.findByUsername(username)
    }

    fun findAll(): MutableIterable<Account> {
        logger.info("🔎 Find all accounts")
        return accountRepository.findAll()
    }

    fun deleteByUUID(id: Long): ResponseEntity<String> {
        try {
            accountRepository.deleteById(id)
            logger.info("🗑️ Deleted account with id: $id")
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Account deleted successfully.")
        } catch (e: NoSuchElementException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found.")
        } catch (e: Exception) {
            logger.error("❗ Could not delete account with id: $id")
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to delete account: ${e.message}")
        }
    }
}