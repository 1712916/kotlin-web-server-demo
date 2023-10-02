package com.example.kotlinwebserverdemo.repository

import com.example.kotlinwebserverdemo.entity.RoleEntity
 import org.springframework.data.jpa.repository.JpaRepository


interface RoleRepository : JpaRepository<RoleEntity, Long> {}
