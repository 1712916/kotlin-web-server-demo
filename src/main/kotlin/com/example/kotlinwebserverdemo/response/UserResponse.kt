package com.example.kotlinwebserverdemo.response

import com.example.kotlinwebserverdemo.entity.UserEntity
import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

data class UserResponse(
    val id: Long,
    val accountName: String,
    val userName: String,
    val email: String,
) {
    companion object {
        fun fromUserEntity(user: UserEntity): UserResponse {
            return UserResponse(
                id = user.id,
                accountName = user.accountName,
                userName = user.userName,
                email = user.email,
            )
        }
    }
}


data class UsersResponse (val total: Long, val users: List<UserResponse>)
