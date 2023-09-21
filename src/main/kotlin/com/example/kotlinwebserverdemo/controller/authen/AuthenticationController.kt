package com.example.kotlinwebserverdemo.controller.authen

import com.example.kotlinwebserverdemo.entity.UserEntity
import com.example.kotlinwebserverdemo.response.Response
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*

/*
 Form:
    - accountName:
    - userName:
    - password: abc..xyzABC...XYZ@!#$~...*0123..9
    - confirmPassword: abc..xyzABC...XYZ@!#$~...*0123..9
    - email: email format
**/
val users: List<UserEntity> = listOf()

@RestController
@RequestMapping("/api/authentication")
@Validated
class AuthenticationController {
    @PostMapping("/sign-in")
    fun signIn(@Valid @RequestBody userForm: SignUpDto): ResponseEntity<Response<UserEntity>> {
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
}

data class SignUpDto(
    @field:NotBlank(message = "AccountName must not be blank")
    val accountName: String,
    @field:NotBlank(message = "UserName must not be blank")
    val userName: String,
    @field:NotBlank(message = "Email must not be blank")
    val email: String,
    @field:NotBlank(message = "Password must not be blank")
    val password: String,
    @field:NotBlank(message = "ConfirmPassword must not be blank")
    val confirmPassword: String,
)

abstract class ValidateMessage(message: String)

abstract class SignUpValidate(message: String) : ValidateMessage(message)
