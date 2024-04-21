package com.example.accountsservice.config

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated

@ConfigurationProperties(prefix = "jwt-security")
@Validated
class SecurityProperties {
    @field:NotBlank
    var secret = "my-32-character-ultra-secure-and-ultra-long-secret"

    @field:NotBlank
    var expirationTime: Int = 31 // in days

    @field:NotBlank
    var strength = 10

    // constant
    val tokenPrefix = "Bearer "
    val headerString = "Authorization"
}