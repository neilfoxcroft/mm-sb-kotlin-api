package com.example.accountsservice.config

import com.example.accountsservice.security.JWTAuthenticationFilter
import com.example.accountsservice.security.TokenProvider
import com.example.accountsservice.service.AppAuthenticationManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
class WebConfig(
    val authenticationManager: AppAuthenticationManager,
    val securityProperties: SecurityProperties,
    val tokenProvider: TokenProvider
) {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        return http.cors { config ->
            config.configurationSource(UrlBasedCorsConfigurationSource().also { cors ->
                CorsConfiguration().apply {
                    allowedOrigins = listOf("*")
                    allowedMethods = listOf("POST", "PUT", "DELETE", "GET", "OPTIONS", "HEAD")
                    allowedHeaders = listOf(
                        "Authorization",
                        "Content-Type",
                        "X-Requested-With",
                        "Accept",
                        "Origin",
                        "Access-Control-Request-Method",
                        "Access-Control-Request-Headers"
                    )
                    exposedHeaders = listOf(
                        "Access-Control-Allow-Origin",
                        "Access-Control-Allow-Credentials",
                        "Authorization",
                        "Content-Disposition"
                    )
                    maxAge = 3600
                    cors.registerCorsConfiguration("/**", this)
                }
            })
        }
            .csrf { csrf -> csrf.disable() }
            .sessionManagement { sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests
                    .requestMatchers(HttpMethod.GET, "/actuator/health/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/actuator/info/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/account/register").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/account/login").permitAll()
                    .anyRequest().permitAll()
            }
            .addFilter(JWTAuthenticationFilter(authenticationManager, securityProperties, tokenProvider))
            .build()
    }
}
