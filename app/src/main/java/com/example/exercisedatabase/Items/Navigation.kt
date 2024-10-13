package com.example.exercisedatabase.Items

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.exercisedatabase.Repository.UsuarioRepositorio

@Composable
fun navigation(usuarioRepositorio: UsuarioRepositorio){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "singIn"){
        composable("singIn"){
            singIngScreen(navController)
        }
        composable("singUp"){
            SingUp(navController, usuarioRepositorio)
        }
        composable("publicationList"){
            publicationList(
                username = "John Doe",
                description = "Lorem ipsum dolor sit amet...",
                navController
            )
        }
    }
}