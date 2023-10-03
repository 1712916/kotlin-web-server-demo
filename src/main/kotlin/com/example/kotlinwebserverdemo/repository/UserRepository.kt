package com.example.kotlinwebserverdemo.repository

import com.example.kotlinwebserverdemo.entity.UserEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface UserRepository : PagingAndSortingRepository<UserEntity, Long>,
    CrudRepository<UserEntity, Long> {
    fun findByUserNameContains(userName: String?, pageable: Pageable): Page<UserEntity>?
    fun countByUserNameContains(userName: String?): Long
}
