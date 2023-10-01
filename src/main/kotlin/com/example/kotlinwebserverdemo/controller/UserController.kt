package com.example.kotlinwebserverdemo.controller

import com.example.kotlinwebserverdemo.dto.UserDto
import com.example.kotlinwebserverdemo.entity.UserEntity
import com.example.kotlinwebserverdemo.response.Response
import com.example.kotlinwebserverdemo.response.UserResponse
import com.example.kotlinwebserverdemo.response.UsersResponse
import com.example.kotlinwebserverdemo.service.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
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
    fun users(@RequestBody(required = false) dto: UserDto?): ResponseEntity<Response<UsersResponse>> {
       val userDto = dto ?: UserDto()
        val users = userService.getUsers(userDto.page, userDto.size)
        return ResponseEntity.ok(
            Response(
                message = "Successful",
                data = UsersResponse(users = users.mapIndexed { _, user -> UserResponse.fromUserEntity(user)},
                    total = userService.getTotalUser(),
            )
        ))
    }
}
