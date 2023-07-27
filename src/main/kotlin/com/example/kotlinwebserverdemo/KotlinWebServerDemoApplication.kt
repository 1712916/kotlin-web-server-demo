package com.example.kotlinwebserverdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class KotlinWebServerDemoApplication

fun main(args: Array<String>) {
    runApplication<KotlinWebServerDemoApplication>(*args)
}

@RestController
class TasksController {
    //API logic here
    @GetMapping("/hello-world/{name}")
    fun getTask(@PathVariable name: String): String {
        //Fetch task with the provided id and return it
        return "Hello world $name"
    }
}