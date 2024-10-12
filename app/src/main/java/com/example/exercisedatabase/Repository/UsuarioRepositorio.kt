package com.example.exercisedatabase.Repository

import com.example.exercisedatabase.DAO.UsuarioDao
import com.example.exercisedatabase.Model.Usuario

class UsuarioRepositorio(private val usuarioDao: UsuarioDao){

    suspend fun insert(usuario: Usuario){
        return usuarioDao.insert(usuario)
    }

    suspend fun getAllUsers():List<Usuario>{
        return usuarioDao.getAllUsers()
    }

    suspend fun deleteById(userId: Int): Int{
        return usuarioDao.deleteById(userId)
    }

    suspend fun updateById(userId: Int, username: String, password: String): Int{
        return usuarioDao.updateById(userId, username, password)
    }

}

