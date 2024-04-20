package com.example.accountsservice.repository

import com.example.accountsservice.model.Account
import java.util.Optional
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : CrudRepository<Account, Long> {
    fun findByUserName(username: String): Optional<Account>
}