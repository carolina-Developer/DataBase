package com.example.exercisedatabase.Repository

import com.example.exercisedatabase.DAO.PublicacionDao
import com.example.exercisedatabase.Model.Publicacion

class PublicacionRepositorio(private val publicacionDao: PublicacionDao){

    suspend fun insert(publicacion: Publicacion){
        return publicacionDao.insert(publicacion)
    }

    suspend fun getAllPublicactions():List<Publicacion>{
        return publicacionDao.getAllPublicactions()
    }

    suspend fun deleteById(publicacionId: Int): Int{
        return publicacionDao.deleteById(publicacionId)
    }

    suspend fun updateById(publicacionId: Int, titulo: String, cuerpo: String): Int{
        return publicacionDao.updateById(publicacionId, titulo, cuerpo)
    }
}