package com.example.kotlinwebserverdemo.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import org.springframework.boot.autoconfigure.EnableAutoConfiguration

@Entity
@Table(name = "users")
@EnableAutoConfiguration
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
    @Column(unique = true)
    val accountName: String,
    val userName: String,
    @Column(unique = true)
    val email: String,
    val password: String,
)
