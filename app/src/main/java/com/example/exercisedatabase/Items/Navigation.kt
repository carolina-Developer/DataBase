package com.example.exercisedatabase.Items

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.exercisedatabase.Model.Publicacion
import com.example.exercisedatabase.Model.Usuario
import com.example.exercisedatabase.Repository.PublicacionRepositorio
import com.example.exercisedatabase.Repository.UsuarioRepositorio

@Composable
fun navigation(usuarioRepositorio: UsuarioRepositorio, publicacionRepositorio: PublicacionRepositorio){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "singIn"){
        composable("singIn"){
            singIngScreen(navController, usuarioRepositorio)
        }
        composable("singUp"){
            SingUp(navController, usuarioRepositorio)
        }
        composable("publicatins"){
            publicationsNav(navController,publicacionRepositorio)
        }
    }
}