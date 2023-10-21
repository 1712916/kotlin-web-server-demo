package com.example.kotlinwebserverdemo.controller

import com.example.kotlinwebserverdemo.entity.FileEntity
import com.example.kotlinwebserverdemo.response.Response
import com.example.kotlinwebserverdemo.service.UploadFileService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/file")
class UploadFileController(private  val service: UploadFileService) {

    //upload files -> return urls
    @PostMapping("/upload",  consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun uploadFiles(@RequestPart("files") files: List<MultipartFile>?,) :  ResponseEntity<Response<String>> {
        return ResponseEntity.ok(
            Response<String>(
                message = "successful", data = service.uploadFiles(files ?: emptyList()).toString(),
            )
        )
    }


    //get file by id
    @GetMapping("/{id}")
    fun userById(@PathVariable id: Long) :  ResponseEntity<Response<FileEntity>> {
        return ResponseEntity.ok(
            Response<FileEntity>(
                message = "successful", data = service.getFile(id),
            )
        )
    }

    //delete file by id
    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long) :  ResponseEntity<Response<Boolean>> {
        return ResponseEntity.ok(
            Response<Boolean>(
                message = "successful", data = service.deleteFile(id),
            )
        )
    }
}
