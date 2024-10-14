package com.example.exercisedatabase.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exercisedatabase.DAO.PublicationWithDetailsDAO
import com.example.exercisedatabase.Model.Publicacion
import com.example.exercisedatabase.Model.PublicacionWithDetails
import com.example.exercisedatabase.Model.Usuario

@Database(entities = [PublicacionWithDetails::class, Publicacion:: class, Usuario::class], version = 1, exportSchema = false)

abstract class PublicacionWithDetailsDatabase : RoomDatabase() {
    abstract fun publicacionWithDetailsDao(): PublicationWithDetailsDAO

    companion object {
        @Volatile
        private var INSTANCE: PublicacionWithDetailsDatabase? = null
        fun getDatabasePublicationWithDetails(context: Context): PublicacionWithDetailsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PublicacionWithDetailsDatabase::class.java,
                    "publicacionWithDetails_database"
                ).build()
                INSTANCE = instance
                instance
            }

        }
    }
}