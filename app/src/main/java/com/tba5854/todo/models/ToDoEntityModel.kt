package com.tba5854.todo.models

data class ToDoEntityModel(
    val id: Long,
    val title: String,
    val description: String,
    val isCompleted: Boolean,
)