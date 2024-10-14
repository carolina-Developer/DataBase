package com.example.exercisedatabase.DAO

import androidx.room.Dao
import androidx.room.Query
import androidx.room.RoomWarnings
import com.example.exercisedatabase.Model.PublicacionWithDetails

@Dao
interface PublicationWithDetailsDAO {
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query(
        """SELECT u.username, p.subject, p.description
        FROM publicacion as p
        INNER JOIN usuario as u
        ON p.user_id = u.user_id"""
    )
    suspend fun getPublicacionByUsername(): List<PublicacionWithDetails>
}