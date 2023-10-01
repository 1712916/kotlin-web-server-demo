package com.example.kotlinwebserverdemo.service

import com.example.kotlinwebserverdemo.entity.UserEntity
import com.example.kotlinwebserverdemo.repository.UserRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class  UserService(val userRepository: UserRepository) {
    fun getUserById(id: Long): UserEntity? {
        return  userRepository.findById(id).getOrNull()
    }

    fun getUsers(page: Int, size: Int ): List<UserEntity> {
        return userRepository.findAll(PageRequest.of(page, size)).toList()
    }

    fun getTotalUser() : Long {
        return userRepository.count()
    }
}
