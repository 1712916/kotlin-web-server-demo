package com.example.kotlinwebserverdemo.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.boot.autoconfigure.EnableAutoConfiguration

@Entity
@Table(name = "files")
@EnableAutoConfiguration
data class FileEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
    val type: String,
    val url: String,
    val description: String,
)
