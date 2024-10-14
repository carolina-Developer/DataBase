package com.example.exercisedatabase.Model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "publicationWithDetails")
data class PublicacionWithDetails(
    @PrimaryKey(autoGenerate = true)
    val id_publicacionWithDetails: Int = 0,
    @Ignore val username: String,
    val subject: String,
    val description: String,

    )
