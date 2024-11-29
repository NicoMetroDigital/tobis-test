package com.example.todo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.example.todo"])
class ToDoApplication

fun main(args: Array<String>) {
    runApplication<ToDoApplication>(*args)
}

