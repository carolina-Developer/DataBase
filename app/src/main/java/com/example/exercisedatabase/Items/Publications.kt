package com.example.exercisedatabase.Items

import android.annotation.SuppressLint
import android.widget.PopupMenu
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.exercisedatabase.R
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun publicationList(
username: String,
description: String,
navController: NavController
) {
    val drawerState = rememberDrawerState(initialValue =DrawerValue.Closed) //Recuerda el valor del drwaer
    val scope = rememberCoroutineScope() //Recuerda cuando este abierto o cerrado

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent()
            }
        },
        gesturesEnabled = true
    ) {
        Scaffold(
            topBar = {
                topBar(
                    onOpenDrawer = {
                        scope.launch {
                            drawerState.apply {
                                if(isClosed) open() else close()
                            }
                        }
                    }
                )
            }
        ) { padding ->
            screenContent(modifier = Modifier.padding(padding))
        }
    }
}
@Composable
fun DrawerContent(
    modifier: Modifier = Modifier
){
    var showDialog by remember { mutableStateOf(false) }
    
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "Logo",
    )
    
    Text(
        text = "Publications App",
        fontSize = 24.sp,
        modifier = Modifier.padding(16.dp)
    )
    HorizontalDivider()
    NavigationDrawerItem(
        icon = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add"
            )
        },
        label = {
            Text(
                text = "Add Publication",
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
        },
        selected = false,
        onClick = {
            showDialog = true
        }
    )
    if (showDialog) {
        createPublication(
            onDismiss = { showDialog = false },
            onSend = { showDialog = false }
        )
    }
}

@Composable
fun screenContent(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .padding(30.dp)
            .fillMaxSize()
    ){
        publications(username = "JuanPrieto", description = "Lorem ipsum dolor sit amet...")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topBar(
    onOpenDrawer: () -> Unit
){
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(0.6f)
        ),
        navigationIcon = {
            Icon(
                modifier = Modifier
                    .padding(start = 16.dp, end = 8.dp)
                    .size(28.dp)
                    .clickable {
                        onOpenDrawer()
                    },
                imageVector = Icons.Default.Menu ,
                contentDescription = "Menu"
            )
        },
        title = {
            Text(
                text = "Publications",
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            Icon(
                modifier = Modifier
                    .padding(start = 8.dp, end = 16.dp)
                    .size(30.dp),
                imageVector = Icons.Default.AccountCircle ,
                contentDescription = "Menu"
            )
            Icon(
                modifier = Modifier
                    .padding(start = 8.dp, end = 16.dp)
                    .size(30.dp),
                imageVector = Icons.Default.Add ,
                contentDescription = "Menu"
            )
        }
    )
}
@Composable
fun addPublication(
onDismiss: () -> Unit,
onSend: () -> Unit
) {
    var subject by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id
                        = R.drawable.user),
                        contentDescription = "User"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    TextField(
                        value = message,
                        onValueChange = { message = it },
                        label = { Text("Message") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = onDismiss
                    ) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = onSend
                    ) {
                        Text("Send")
                    }
                }
            }
        }
    }
}

@Composable
fun publications(
    username: String,
    description: String
){
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .clip(RoundedCornerShape(19.dp)),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 1.dp)
                .background(
                    Color(0xFFE0E0E0)
                )
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 19.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Image(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.Top)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(7.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        modifier = Modifier.padding(top = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = username,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "@${username}"
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = description
                    )
                }
            }
        }
    }
}
