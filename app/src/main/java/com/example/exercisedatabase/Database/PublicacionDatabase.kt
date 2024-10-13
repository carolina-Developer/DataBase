package com.example.exercisedatabase.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exercisedatabase.DAO.PublicacionDao
import com.example.exercisedatabase.DAO.UsuarioDao
import com.example.exercisedatabase.Model.Publicacion
import com.example.exercisedatabase.Model.Usuario

class PublicacionDatabase {
    //Se esta haciendo la instancia de la clase "Usuario"
    @Database(entities = [Publicacion::class], version = 1, exportSchema = false)

    //Asbtracto porque solo el Room hace la instancia
    abstract class PublicacionDatabase : RoomDatabase() {

        //Esto es un objeto estático que se puede utilizar para obtener la instancia de la base de datos
        abstract fun publicacionDao(): PublicacionDao

        companion object {
            //Cualquier hilo(Proceso) pueda acceder a la variable mas actualizada
            @Volatile
            private var INSTANCE: PublicacionDatabase? = null

            //Permite crear la instancia de la base de datos
            fun getDatabasePublicaion(context: Context): PublicacionDatabase {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        PublicacionDatabase::class.java,
                        "publicacion_database"
                    ).build()
                    INSTANCE = instance
                    instance
                }

            }
        }

    }
}