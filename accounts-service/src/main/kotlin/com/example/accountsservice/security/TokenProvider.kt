package com.example.accountsservice.security

import com.example.accountsservice.config.SecurityProperties
import com.example.accountsservice.repository.AccountRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import java.util.Calendar
import java.util.Date
import javax.crypto.SecretKey
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component


@Component
class TokenProvider(
    private val securityProperties: SecurityProperties,
    private val accountRepository: AccountRepository,
) {
    private var key: SecretKey? = null

    @PostConstruct
    fun init() {
        key = Keys.hmacShaKeyFor(securityProperties.secret.toByteArray())
    }

    fun createToken(authentication: Authentication): String {
        val tokenValidity = Date().add(Calendar.DAY_OF_MONTH, securityProperties.expirationTime)
        val authClaims: MutableList<String> = mutableListOf()
        authentication.authorities?.let { authorities ->
            authorities.forEach { claim -> authClaims.add(claim.toString()) }
        }

        return Jwts.builder()
            .subject(authentication.name)
            .claim("auth", authClaims)
            .expiration(tokenValidity)
            .signWith(key)
            .compact()
    }

    fun getAuthentication(token: String): Authentication? {
        return try {
            val claims = Jwts.parser()
                .verifyWith(key)
                .clockSkewSeconds(3 * 60)
                .build()
                .parseSignedClaims(token.replace(securityProperties.tokenPrefix, ""))
            val accountDetail = accountRepository.findByUsername(claims.payload.subject)
            UsernamePasswordAuthenticationToken(accountDetail, token)
        } catch (e: Exception) {
            return null
        }
    }

    fun Date.add(field: Int, amount: Int): Date {
        Calendar.getInstance().apply {
            time = this@add
            add(field, amount)
            return time
        }
    }

}