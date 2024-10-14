package com.example.exercisedatabase.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "publicacion")
data class Publicacion(
        @PrimaryKey(autoGenerate = true)
        val id_publicacion: Int = 0,
        val subject: String = "",
        val description: String = "",
        val user_id: Int = 0
    )

