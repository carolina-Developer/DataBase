package com.example.exercisedatabase.Repository

import com.example.exercisedatabase.DAO.PublicationWithDetailsDAO
import com.example.exercisedatabase.Model.PublicacionWithDetails

class PublicationWithDetailsRepositorio(private val publicacionWithDetailsDao: PublicationWithDetailsDAO){

    suspend fun getPublicacionByUsername(): List<PublicacionWithDetails>{
        return publicacionWithDetailsDao.getPublicacionByUsername()
    }
}