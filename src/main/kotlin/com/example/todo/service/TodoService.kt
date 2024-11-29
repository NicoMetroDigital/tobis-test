package com.example.todo.service

import com.example.todo.repository.TodoRepository
import com.example.todo.model.Todo
import org.springframework.stereotype.Service

@Service
class TodoService(private val todoRepository: TodoRepository) {
    fun getAllTodos(): List<Todo> = todoRepository.findAll()

    fun getTodoById(id: Long): Todo? = todoRepository.findById(id).orElse(null)

    fun createTodo(todo: Todo): Todo = todoRepository.save(todo)

    fun updateTodo(id: Long, updatedTodo: Todo): Todo? {
        return if (todoRepository.existsById(id)) {
            updatedTodo.id = id
            todoRepository.save(updatedTodo)
        } else null
    }

    fun deleteTodoById(id: Long): Boolean {
        return if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id)
            true // Rückgabe true bedeutet, dass das Löschen erfolgreich war
        } else {
            false // Rückgabe false bedeutet, dass die ID nicht existiert
        }
    }

}
