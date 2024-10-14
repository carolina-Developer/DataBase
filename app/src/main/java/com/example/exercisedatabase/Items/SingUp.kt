package com.example.exercisedatabase.Items

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.exercisedatabase.Model.Usuario
import com.example.exercisedatabase.R
import com.example.exercisedatabase.Repository.UsuarioRepositorio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun SingUp(navController: NavController, usuarioRepositorio: UsuarioRepositorio) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()

        .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement
    = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id
                    = R.drawable.user), // Reemplaza con tu imagen
        contentDescription = "Avatar del usuario",
        modifier = Modifier.size(120.dp)
        )

        Text(
            text = "Create your account",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Create an account and access exclusive content.",
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 8.dp)
        )

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Button(
            onClick = {
                val user = Usuario(
                    username = username,
                    password = password
                )
                scope.launch {
                    withContext(Dispatchers.IO) {
                        usuarioRepositorio.insert(user)
                    }
                }
                Toast.makeText(context, "User registered", Toast.LENGTH_SHORT).show()
                navController.navigate("publicatins")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Sign Up")
        }
    }
}