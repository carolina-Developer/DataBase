package com.example.exercisedatabase.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    val user_id: Int = 0,
    val username: String = "",
    val password: String = ""
)