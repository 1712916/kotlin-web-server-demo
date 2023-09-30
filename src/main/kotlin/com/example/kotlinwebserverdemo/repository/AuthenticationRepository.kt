package com.example.kotlinwebserverdemo.repository

import com.example.kotlinwebserverdemo.entity.UserEntity
import org.springframework.data.repository.CrudRepository


interface AuthenticationRepository : CrudRepository<UserEntity, Long> {
    fun findByAccountName(accountName: String): UserEntity?
    fun findByEmail(email: String): UserEntity?
}

interface UserRepository : org.springframework.data.repository.Repository<UserEntity, Long>
