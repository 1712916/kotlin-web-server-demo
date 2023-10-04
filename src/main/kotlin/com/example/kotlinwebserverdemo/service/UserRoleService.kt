package com.example.kotlinwebserverdemo.service

import com.example.kotlinwebserverdemo.entity.UserEntity
import com.example.kotlinwebserverdemo.entity.UserRoleEntity
import com.example.kotlinwebserverdemo.repository.RoleRepository
import com.example.kotlinwebserverdemo.repository.UserRoleRepository
import org.springframework.stereotype.Service

private const val Default_Role_Id : Long = 0
@Service
class UserRoleService(private val userRoleRepository: UserRoleRepository, private  val roleRepository: RoleRepository) {

    fun saveDefaultUser(user: UserEntity) {
        userRoleRepository.save(UserRoleEntity(
            user = user,
            role = roleRepository.findById(Default_Role_Id).get(),
        ),)
    }
}
