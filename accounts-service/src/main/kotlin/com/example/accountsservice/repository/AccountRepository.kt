package com.example.accountsservice.repository

import com.example.accountsservice.model.Account
import java.util.Optional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<Account, Long> {
    fun findByUsername(username: String): Optional<Account>
    fun existsByUsername(username: String): Boolean
}