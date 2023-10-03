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

    fun getUsers(page: Int, size: Int, userName: String? ): List<UserEntity> {
        if (userName != null) {
            return userRepository.findByUserNameContains(userName = userName, pageable = PageRequest.of(page, size))?.toList() ?: emptyList();
        }

        return userRepository.findAll(PageRequest.of(page, size)).toList()
    }

    fun getTotalUser( userName: String?) : Long {
        if (userName != null) {
            return userRepository.countByUserNameContains(userName)
        }
        return userRepository.count()
    }


}
