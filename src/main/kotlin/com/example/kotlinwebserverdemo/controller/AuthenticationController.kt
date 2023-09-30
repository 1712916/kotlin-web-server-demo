package com.example.kotlinwebserverdemo.controller
//import org.springframework.data.jpa.repository.Query
//import org.springframework.stereotype.Repository
import com.example.kotlinwebserverdemo.dto.SignInDto
import com.example.kotlinwebserverdemo.dto.SignUpDto
import com.example.kotlinwebserverdemo.entity.UserEntity
import com.example.kotlinwebserverdemo.response.Response
import com.example.kotlinwebserverdemo.service.AuthenticationService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

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
    fun signIn(@Valid @RequestBody userForm: SignInDto): ResponseEntity<Response<String>> {
        return ResponseEntity.ok(
            Response<String>(
                message = "Login successful", data = authenticationService.signIn(userForm)
            )
        )
    }

    @PostMapping("/sign-up")
    fun signUp(@Valid @RequestBody userForm: SignUpDto): ResponseEntity<Response<UserEntity>> {
        return ResponseEntity.ok(
            Response(
                message = "Login successful",
                data = authenticationService.register(userForm)
            )
        )
    }

    @PostMapping("/check-expire-token")
    fun checkExpireToken(@Valid @RequestBody token: String): ResponseEntity<Response<Boolean>> {
        val  isExpired = authenticationService.checkExpiredToken(token)

        return ResponseEntity.ok(
            Response<Boolean>(
                message =if (isExpired) "Token is expired" else "Token is not expired",
                data = isExpired
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
