package com.example.kotlinwebserverdemo.repository

import com.example.kotlinwebserverdemo.entity.UserEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface UserRepository : PagingAndSortingRepository<UserEntity, Long>,
    CrudRepository<UserEntity, Long>
