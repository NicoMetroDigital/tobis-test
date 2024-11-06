package com.example.todo  // Passe das Paket entsprechend an

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ToDoApplication

fun main(args: Array<String>) {
    runApplication<ToDoApplication>(*args)
}
