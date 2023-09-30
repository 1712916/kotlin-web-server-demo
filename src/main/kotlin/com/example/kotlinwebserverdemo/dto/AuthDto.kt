package com.example.kotlinwebserverdemo.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class SignUpDto(
    @field:NotBlank(message = "AccountName must not be blank")
    val accountName: String,
    @field:NotBlank(message = "UserName must not be blank")
    val userName: String,
    @field:NotBlank(message = "Email must not be blank")
    @field:Email(message = "Email is invalid")
    val email: String,
    @field:NotBlank(message = "Password must not be blank")
    val password: String,
    @field:NotBlank(message = "ConfirmPassword must not be blank")
    val confirmPassword: String,
)

data class SignInDto(
    @field:NotBlank(message = "AccountName must not be blank")
    val accountName: String,
    @field:NotBlank(message = "Password must not be blank")
    val password: String,
)
