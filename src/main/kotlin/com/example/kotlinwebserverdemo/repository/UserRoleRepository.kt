package com.example.kotlinwebserverdemo.repository

import com.example.kotlinwebserverdemo.entity.RoleEntity
import com.example.kotlinwebserverdemo.entity.UserEntity
import com.example.kotlinwebserverdemo.entity.UserRoleEntity
import org.springframework.data.repository.CrudRepository


interface UserRoleRepository : CrudRepository<UserRoleEntity, Long> {
    fun findAllByUser(user: UserEntity) : List<UserRoleEntity>
}
