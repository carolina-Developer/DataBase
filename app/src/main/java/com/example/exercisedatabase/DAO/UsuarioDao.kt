package com.example.exercisedatabase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exercisedatabase.Model.Usuario

@Dao
interface UsuarioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(usuario: Usuario)

    @Query("SELECT * FROM usuario")
    suspend fun getAllUsers(): List<Usuario>

    @Query("DELETE FROM usuario WHERE id = :userId")
    suspend fun deleteById(userId: Int): Int

    @Query("UPDATE usuario SET username = :username, password = :password WHERE id= :userId")
    suspend fun updateById(userId: Int, username: String, password: String): Int



}