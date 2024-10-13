package com.example.exercisedatabase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exercisedatabase.Model.Publicacion
import com.example.exercisedatabase.Model.Usuario


@Dao
interface PublicacionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(publicacion: Publicacion)

    @Query("SELECT * FROM publicacion")
    suspend fun getAllPublicactions(): List<Publicacion>

    @Query("DELETE FROM publicacion WHERE id = :publicacionId")
    suspend fun deleteById(publicacionId: Int): Int

    @Query("UPDATE publicacion SET titulo = :titulo, cuerpo = :cuerpo WHERE id = :publicacionId")
    suspend fun updateById(publicacionId: Int, titulo: String, cuerpo: String): Int
}