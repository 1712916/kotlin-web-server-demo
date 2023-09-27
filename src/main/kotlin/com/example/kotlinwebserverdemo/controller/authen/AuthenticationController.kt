package com.example.kotlinwebserverdemo.controller.authen

import com.example.kotlinwebserverdemo.entity.UserEntity
import com.example.kotlinwebserverdemo.response.Response
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.springframework.data.repository.CrudRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.Optional

/*
 Form:
    - accountName:
    - userName:
    - password: abc..xyzABC...XYZ@!#$~...*0123..9
    - confirmPassword: abc..xyzABC...XYZ@!#$~...*0123..9
    - email: email format
**/

@RestController
@RequestMapping("/api/authentication")
@Validated
class AuthenticationController(val authenticationService: AuthenticationService) {
    @PostMapping("/sign-in")
    fun signIn(@Valid @RequestBody userForm: SignInDto): ResponseEntity<Response<UserEntity>> {
        println(userForm)
        //Fetch task with the provided id and return it
        return ResponseEntity.ok(
            Response<UserEntity>(
                message = "Login successful", data = UserEntity(
                    id = 1,
                    accountName = "String",
                    userName = "String",
                    email = "String",
                )
            )
        )
    }

    @PostMapping("/sign-up")
    fun signUp(@Valid @RequestBody userForm: SignUpDto): ResponseEntity<Response<UserEntity>> {
        SignUpValidator(userForm).validate()

        return ResponseEntity.ok(
            Response(
                message = "Login successful",
                data = authenticationService.addUser(userForm)
            )
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(ex: MethodArgumentNotValidException): ResponseEntity<Response<Any>> {
        val result = ex.bindingResult
        val errors = result.fieldErrors
        var message = ""
        for (error in errors) {
            message += "${error.defaultMessage}\n"
        }
        return ResponseEntity.badRequest().body(Response(message = message))
    }

    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatusException(ex: ResponseStatusException): ResponseEntity<Response<Any>> {
        return ResponseEntity.badRequest().body(Response(message = ex.reason ?: "have an error"))
    }
}

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

class SignUpValidator(private val signUpDto: SignUpDto) {
    fun validate() {
        if (signUpDto.password != signUpDto.confirmPassword)
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Confirm password is not match with your password\""
            )
    }
}

@Service
class AuthenticationService(private val authenticationRepository: AuthenticationRepository) {
    fun addUser(signUpDto: SignUpDto): UserEntity {
        return authenticationRepository.save(
            UserEntity(
                accountName = signUpDto.accountName,
                userName = signUpDto.userName,
                email = signUpDto.email,
            )
        )

    }

    fun findOne(signInDto: SignInDto): UserEntity {
        return authenticationRepository.findById(1).get()
    }
}

interface AuthenticationRepository : CrudRepository<UserEntity, Long>
