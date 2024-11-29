package com.example.todo.controller

import com.example.todo.service.TodoService
import com.example.todo.model.Todo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        // CORS für alle Endpunkte konfigurieren
        registry.addMapping("/**")  // Alle Endpunkte
            .allowedOrigins("http://localhost:3000") // Erlaubt nur Anfragen von localhost:3000
            .allowedMethods("GET", "POST", "PUT", "DELETE") // Erlaubt nur diese HTTP-Methoden
            .allowedHeaders("*") // Erlaubt alle Header
            .allowCredentials(true) // Erlaubt Cookies und Credentials
    }
}


@RestController
@RequestMapping("/api/todos")
class TodoController(private val todoService: TodoService) {

    @GetMapping
    fun getAllTodos(): List<Todo> = todoService.getAllTodos()

    @GetMapping("/{id}")
    fun getTodoById(@PathVariable id: Long): ResponseEntity<Todo> {
        val todo = todoService.getTodoById(id)
        return if (todo != null) ResponseEntity.ok(todo) else ResponseEntity.notFound().build()
    }

    @PostMapping
    fun createTodo(@RequestBody todo: Todo): ResponseEntity<Todo> {
        val createdTodo = todoService.createTodo(todo)
        return ResponseEntity(createdTodo, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateTodo(@PathVariable id: Long, @RequestBody updatedTodo: Todo): ResponseEntity<Todo> {
        val todo = todoService.updateTodo(id, updatedTodo)
        return if (todo != null) ResponseEntity.ok(todo) else ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun deleteTodoById(@PathVariable id: Long): ResponseEntity<Void> {
        return if (todoService.deleteTodoById(id)) {
            ResponseEntity.noContent().build() // Erfolgreiches Löschen
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build() // ID nicht gefunden
        }
    }
}
