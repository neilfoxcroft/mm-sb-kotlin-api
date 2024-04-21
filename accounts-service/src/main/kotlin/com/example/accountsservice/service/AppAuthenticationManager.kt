package com.example.accountsservice.service

import com.example.accountsservice.model.Account
import com.example.accountsservice.repository.AccountRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component


@Component
class AppAuthenticationManager(
    private val accountRepository: AccountRepository,
    val bCryptPasswordEncoder: BCryptPasswordEncoder,
) : AuthenticationManager {
    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        val password = authentication.credentials.toString()
        val account: Account = accountRepository.findByUsername(authentication.name).orElseThrow {
            UsernameNotFoundException("The username ${authentication.name} doesn't exist")
        }
        if (!bCryptPasswordEncoder.matches(password, account.password)) {
            throw BadCredentialsException("Bad credentials")
        }
        return UsernamePasswordAuthenticationToken(account.username, account.password)
    }
}