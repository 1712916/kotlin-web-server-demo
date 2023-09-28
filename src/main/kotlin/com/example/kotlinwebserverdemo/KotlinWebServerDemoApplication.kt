package com.example.kotlinwebserverdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class ])
class KotlinWebServerDemoApplication

fun main(args: Array<String>) {
    runApplication<KotlinWebServerDemoApplication>(*args)
}
