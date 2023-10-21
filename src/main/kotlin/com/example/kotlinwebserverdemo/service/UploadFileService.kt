package com.example.kotlinwebserverdemo.service

import com.example.kotlinwebserverdemo.entity.FileEntity
import com.example.kotlinwebserverdemo.repository.UploadFileRepository
import com.example.kotlinwebserverdemo.repository.UserRoleRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.Optional

@Service
class UploadFileService(private val fileRepository: UploadFileRepository) {
    fun uploadFiles(files: List<MultipartFile>): List<FileEntity> {
        if (files.isEmpty()) {
            throw  Exception("No files to upload")
        }

        //storage to database
        return  fileRepository.saveAll(files.map { f -> FileEntity(
            type = f.contentType.toString(),
            url = GetUrlFile().uploadFile(f),
           description = "",
        ) }.toList())

    }

    fun deleteFile(id: Long): Boolean? {
        return try {
            fileRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getFile(id: Long): Optional<FileEntity> {
        return fileRepository.findById(id)
    }

}

class GetUrlFile {
    fun uploadFile(file: MultipartFile): String {
        //todo: find a way to storage a file
        return  ""
    }
}
