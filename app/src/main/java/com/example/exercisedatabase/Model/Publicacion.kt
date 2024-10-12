package com.example.exercisedatabase.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "publicacion")
data class Publicacion(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val titulo: String = "",
        val cuerpo: String = "",
        val usuarioId: Int = 0
    )

