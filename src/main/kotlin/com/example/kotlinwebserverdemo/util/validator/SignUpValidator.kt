package com.example.kotlinwebserverdemo.util.validator

import com.example.kotlinwebserverdemo.dto.SignUpDto
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

interface Validator {
    fun validate()
}

class SignUpValidator(private val signUpDto: SignUpDto) : Validator {
    override fun validate() {
        if (signUpDto.password != signUpDto.confirmPassword)
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Confirm password is not match with your password\""
            )
    }
}
