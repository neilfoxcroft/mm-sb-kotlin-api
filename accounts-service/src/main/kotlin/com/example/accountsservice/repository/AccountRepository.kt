package com.example.accountsservice.repository

import com.example.accountsservice.model.Account
import java.util.Optional
import java.util.UUID
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : CrudRepository<Account, UUID> {

    fun findByUserName(username: String): Optional<Account>
    fun findByAccountId(id: UUID): Optional<Account>
}