package com.example.todo.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
data class Todo(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0L, // ID als nicht-nullable setzen
    val title: String,
    val description: String,
    val isComplete: Boolean = false
) {
    constructor() : this(0L, "", false.toString()) // Default-Werte setzen
}


