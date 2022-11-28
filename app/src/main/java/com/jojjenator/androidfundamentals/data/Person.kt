package com.jojjenator.androidfundamentals.data

import java.io.Serializable

// DATA CLASS, only need constructor because it only holds data.
// Must be serializable in order to be transferable between activities.
data class Person (
    val name: String,
    val age: Int,
    val country: String,
) : Serializable // Serializable is a interface..