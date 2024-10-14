package com.example.exercisedatabase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exercisedatabase.Model.Publicacion
import com.example.exercisedatabase.Model.PublicacionWithDetails
import com.example.exercisedatabase.Model.Usuario


@Dao
interface PublicacionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(publicacion: Publicacion)

    @Query("SELECT * FROM publicacion")
    suspend fun getAllPublicactions(): List<Publicacion>

    @Query("DELETE FROM publicacion WHERE id_publicacion = :id_publicacion")
    suspend fun deleteById(id_publicacion: Int): Int

    @Query("UPDATE publicacion SET subject = :subject, description = :description WHERE id_publicacion = :id_publicacion")
    suspend fun updateById(id_publicacion: Int, subject: String, description: String): Int

    /*@Query(
        """SELECT u.username, p.subject, p.description
        FROM publicacion as p
        INNER JOIN usuario as u
        ON p.user_id = u.id"""
    )
    suspend fun getPublicacionByUsername(): List<PublicacionWithDetails>*/

}