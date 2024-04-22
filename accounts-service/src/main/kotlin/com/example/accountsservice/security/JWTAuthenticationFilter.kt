package com.example.accountsservice.security

import com.example.accountsservice.config.SecurityProperties
import com.example.accountsservice.model.Account
import com.example.accountsservice.service.AppAuthenticationManager
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.io.IOException
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JWTAuthenticationFilter(
    private val authManager: AppAuthenticationManager,
    private val securityProperties: SecurityProperties,
    private val tokenProvider: TokenProvider
) : UsernamePasswordAuthenticationFilter() {

    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(
        req: HttpServletRequest,
        res: HttpServletResponse?
    ): Authentication {
        return try {
            val mapper = jacksonObjectMapper()

            val creds = mapper.readValue<Account>(req.inputStream)

            authManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    creds.username,
                    creds.password,
                    ArrayList()
                )
            )
        } catch (e: IOException) {
            throw AuthenticationServiceException(e.message)
        }
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(
        req: HttpServletRequest,
        res: HttpServletResponse,
        chain: FilterChain?,
        authentication: Authentication
    ) {
        val token = tokenProvider.createToken(authentication)
        res.addHeader(securityProperties.headerString, securityProperties.tokenPrefix + token)
    }
}