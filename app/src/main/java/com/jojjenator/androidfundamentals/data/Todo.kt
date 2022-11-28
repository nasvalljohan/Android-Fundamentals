package com.jojjenator.androidfundamentals.data

// Data class that describes one todo-item.
data class Todo(
    val title: String,
    var isChecked: Boolean
)