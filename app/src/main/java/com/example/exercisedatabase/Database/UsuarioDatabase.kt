package com.example.exercisedatabase.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exercisedatabase.DAO.UsuarioDao
import com.example.exercisedatabase.Model.Usuario

class UsuarioDatabase {

    //Se esta haciendo la instancia de la clase "Usuario"
    @Database(entities = [Usuario::class], version = 1, exportSchema = false)

    //Asbtracto porque solo el Room hace la instancia
    abstract class UsuarioDatabase : RoomDatabase() {

        //Esto es un objeto estático que se puede utilizar para obtener la instancia de la base de datos
        abstract fun userDao(): UsuarioDao

        companion object {
            //Cualquier hilo(Proceso) pueda acceder a la variable mas actualizada
            @Volatile
            private var INSTANCE: UsuarioDatabase? = null

            //Permite crear la instancia de la base de datos
            fun getDatabase(context: Context): UsuarioDatabase {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        UsuarioDatabase::class.java,
                        "usuario_database"
                    ).build()
                    INSTANCE = instance
                    instance
                }

            }
        }

    }
}