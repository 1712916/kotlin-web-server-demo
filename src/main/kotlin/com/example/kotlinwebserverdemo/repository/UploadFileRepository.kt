package com.example.kotlinwebserverdemo.repository

import com.example.kotlinwebserverdemo.entity.FileEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UploadFileRepository : JpaRepository<FileEntity, Long> {}
