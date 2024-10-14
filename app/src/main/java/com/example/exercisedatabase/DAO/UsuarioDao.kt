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

    @Query("DELETE FROM usuario WHERE user_id = :user_id")
    suspend fun deleteById(user_id: Int): Int

    @Query("UPDATE usuario SET username = :username, password = :password WHERE user_id= :userId")
    suspend fun updateById(userId: Int, username: String, password: String): Int

    @Query("SELECT * FROM usuario WHERE username = :username AND password = :password")
    suspend fun login(username: String, password: String): Usuario

}