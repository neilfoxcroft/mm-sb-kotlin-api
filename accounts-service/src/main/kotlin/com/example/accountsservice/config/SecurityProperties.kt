package com.example.accountsservice.config

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated

@ConfigurationProperties(prefix = "jwt-security")
@Validated
class SecurityProperties {
    @field:NotBlank
    var secret = "this-is-a-realy-secure-secret-that-will-never-be-hacked"

    var expirationTime: Int = 31 // in days

    var strength = 10

    // constant
    val tokenPrefix = "Bearer "
    val headerString = "Authorization"
}