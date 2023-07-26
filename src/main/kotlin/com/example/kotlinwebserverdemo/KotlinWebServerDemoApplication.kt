package com.example.kotlinwebserverdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinWebServerDemoApplication

fun main(args: Array<String>) {
	runApplication<KotlinWebServerDemoApplication>(*args)
}
