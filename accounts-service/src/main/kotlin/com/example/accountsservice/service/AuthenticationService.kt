package com.example.accountsservice.service

import com.example.accountsservice.model.Account
import com.example.accountsservice.repository.AccountRepository
import com.example.accountsservice.security.TokenProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val authenticationManager: AppAuthenticationManager,
    private val jwtTokenProvider: TokenProvider,
    private val accountRepository: AccountRepository
) {

    fun login(account: Account): String {

        val authentication: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(account.username, account.password)
        )
        SecurityContextHolder.getContext().authentication = authentication
        return jwtTokenProvider.createToken(authentication)
    }
}