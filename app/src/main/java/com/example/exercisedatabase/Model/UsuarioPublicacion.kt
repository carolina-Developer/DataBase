package com.example.exercisedatabase.Model

import androidx.room.Embedded
import androidx.room.Relation

data class UsuarioPublicacion(
    @Embedded val usuario: Usuario, //Padre
    @Relation(
        parentColumn = "id", //Llave primaria del padre
        entityColumn = "usuarioId" //Llave foranea del hijo
    )
    val publicacion: List<Publicacion>
)