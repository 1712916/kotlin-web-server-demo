package com.example.kotlinwebserverdemo.controller

import com.example.kotlinwebserverdemo.response.PagingResponse
import com.example.kotlinwebserverdemo.response.Response
import com.example.kotlinwebserverdemo.response.UserResponse
import com.example.kotlinwebserverdemo.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController(val userService: UserService) {

    @GetMapping("/{id}")
    fun userById(@PathVariable id: Long): ResponseEntity<Response<UserResponse>> {
        val user = userService.getUserById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(
            Response<UserResponse>(
                message = "Successful",
                data = UserResponse.fromUserEntity(user)
            )
        )
    }

    @GetMapping("")
    fun users(@RequestParam(required = false, defaultValue = "0") page: Int, @RequestParam(required = false, defaultValue = "0") size: Int, @RequestParam(required = false) userName: String? = null): ResponseEntity<Any> {

        return ResponseEntity.ok(
            Response<PagingResponse<UserResponse>>(
                message = "Successful",
                data = PagingResponse<UserResponse>(
                    page = page,
                    size = size,
                    totalRecord = userService.getTotalUser(userName),
                    data = userService.getUsers(page, size, userName).map { userEntity ->  UserResponse.fromUserEntity(userEntity)},
                ),
            )
        )
    }

    @PutMapping("/active/{id}")
    fun activeUser(@PathVariable id: Long, @RequestParam isActive: Boolean): ResponseEntity<Response<Boolean>> {
        return ResponseEntity.ok(
            Response(
                message = "Successful",
                data = userService.activeUser(id, isActive)
            )
        )
    }
}
