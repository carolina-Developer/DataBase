
package com.example.exercisedatabase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.exercisedatabase.DAO.PublicacionDao
import com.example.exercisedatabase.DAO.PublicationWithDetailsDAO
import com.example.exercisedatabase.DAO.UsuarioDao
import com.example.exercisedatabase.Database.PublicacionDatabase
import com.example.exercisedatabase.Database.PublicacionWithDetailsDatabase
import com.example.exercisedatabase.Database.UsuarioDatabase
import com.example.exercisedatabase.Items.SingUp
import com.example.exercisedatabase.Items.createPublication
import com.example.exercisedatabase.Items.navigation
import com.example.exercisedatabase.Items.publicationsNav
import com.example.exercisedatabase.Items.singIngScreen
import com.example.exercisedatabase.Model.PublicacionWithDetails
import com.example.exercisedatabase.Repository.PublicacionRepositorio
import com.example.exercisedatabase.Repository.PublicationWithDetailsRepositorio
import com.example.exercisedatabase.Repository.UsuarioRepositorio
import com.example.exercisedatabase.ui.theme.ExerciseDataBaseTheme

class MainActivity : ComponentActivity() {

    private lateinit var usuarioDao: UsuarioDao
    private lateinit var publicacionDao: PublicacionDao
    private lateinit var publicacionWithDetailsDao: PublicationWithDetailsDAO

    private lateinit var usuarioRepositorio: UsuarioRepositorio
    private lateinit var publicacinRepositorio: PublicacionRepositorio
    private lateinit var publicationWithDetailsRepositorio: PublicationWithDetailsRepositorio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dbUser = UsuarioDatabase.getDatabaseUsuario(applicationContext)
        val dbPublication = PublicacionDatabase.getDatabasePublicaion(applicationContext)
        val dbPublicationWithDetails = PublicacionWithDetailsDatabase.getDatabasePublicationWithDetails(applicationContext)

        usuarioDao = dbUser.userDao()
        publicacionDao = dbPublication.publicacionDao()
        publicacionWithDetailsDao = dbPublicationWithDetails.publicacionWithDetailsDao()

        publicacinRepositorio = PublicacionRepositorio(publicacionDao)
        usuarioRepositorio = UsuarioRepositorio(usuarioDao)

        enableEdgeToEdge()
        setContent {
            navigation(usuarioRepositorio, publicacinRepositorio)
        }
    }
}

