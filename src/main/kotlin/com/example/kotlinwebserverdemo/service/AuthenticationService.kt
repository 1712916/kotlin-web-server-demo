package com.example.kotlinwebserverdemo.service

import com.example.kotlinwebserverdemo.dto.SignInDto
import com.example.kotlinwebserverdemo.dto.SignUpDto
import com.example.kotlinwebserverdemo.entity.UserEntity
import com.example.kotlinwebserverdemo.repository.AuthenticationRepository
import com.example.kotlinwebserverdemo.util.validator.SignUpValidator
import com.example.kotlinwebserverdemo.util.validator.Validator
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
@Component
class AuthenticationService(
    private val authenticationRepository: AuthenticationRepository,
) {
    private val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder()
    fun register(signUpDto: SignUpDto): UserEntity {
        //todo: inject validator
        val validator: Validator = SignUpValidator(signUpDto)

        validator.validate()

        //check
        val existUserByAccountName =
            authenticationRepository.findByAccountName(signUpDto.accountName)
        val existUserByEmail = authenticationRepository.findByEmail(signUpDto.email)

        if (existUserByAccountName != null || existUserByEmail != null) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Account is existed! Try another!"
            )
        }

        return authenticationRepository.save(
            UserEntity(
                accountName = signUpDto.accountName,
                userName = signUpDto.userName,
                email = signUpDto.email,
                password = passwordEncoder.encode(signUpDto.password)
            )
        )
    }

    fun signIn(signInDto: SignInDto): UserEntity {
        val existUserByAccountName =
            authenticationRepository.findByAccountName(signInDto.accountName)
                ?: throw ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "User not found"
                )

        if (!passwordEncoder.matches(signInDto.password, existUserByAccountName.password)) {
            throw ResponseStatusException(
                HttpStatus.UNAUTHORIZED,
                "Wrong password"
            )
        }
        return existUserByAccountName

    }
}