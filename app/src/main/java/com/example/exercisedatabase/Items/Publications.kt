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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.exercisedatabase.Model.Publicacion
import com.example.exercisedatabase.Model.Usuario
import com.example.exercisedatabase.R
import com.example.exercisedatabase.Repository.PublicacionRepositorio
import kotlinx.coroutines.launch
import javax.security.auth.Subject

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun publicationsNav(
navController: NavController,
publicacionRepositorio: PublicacionRepositorio,
) {
    val drawerState = rememberDrawerState(initialValue =DrawerValue.Closed) //Recuerda el valor del drwaer
    val scope = rememberCoroutineScope() //Recuerda cuando este abierto o cerrado

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(publicacionRepositorio)
            }
        },
        gesturesEnabled = true
    ) {
        Scaffold(
            topBar = {
                topBar(
                    navController = navController,
                    onOpenDrawer = {
                        scope.launch {
                            drawerState.apply {
                                if(isClosed) open() else close()
                            }
                        }
                    },
                    publicacionRepositorio
                )
            }
        ) { padding ->
            screenContent(modifier = Modifier.padding(padding),publicacionRepositorio)
        }
    }
}
/******************** MENU ********************/
@Composable
fun DrawerContent(
    publicacionRepositorio: PublicacionRepositorio
){
    Row(){
        Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "Logo",
        modifier = Modifier
            .fillMaxSize()
    )
        Text(
            text = "Configuration App",
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )

            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Add"
            )

    }
    HorizontalDivider()
    var showDialogAdd by remember { mutableStateOf(false) }
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
            showDialogAdd = true
        }
    )
    if (showDialogAdd) {
        createPublication(
            onDismiss = { showDialogAdd = false },
            onSend = { showDialogAdd = false },
            publicacionRepositorio
        )
    }

}
/******************** CONTENT ********************/
@Composable
fun screenContent(
    modifier: Modifier = Modifier,
    publicacionRepositorio: PublicacionRepositorio,
){
    val publications = remember { mutableStateOf(emptyList<Publicacion>()) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch{
            publications.value = publicacionRepositorio.getAllPublicactions()
        }
    }

    Box(
        modifier = modifier
            .padding(15.dp)
            .fillMaxSize()
    ){
            publications(
                username = "User",
                subject = publications.value.firstOrNull()?.subject ?: "",
                description = publications.value.firstOrNull()?.description ?: ""
            )
    }
}
/******************** TOP BAR ********************/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topBar(
    navController: NavController,
    onOpenDrawer: () -> Unit,
    publicacionRepositorio: PublicacionRepositorio
){
    var showDialogAdd by remember { mutableStateOf(false) }
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
            IconButton(
                onClick = {
                    navController.navigate("singIn")
                }
            ) {
                Icon(
                    modifier = Modifier
                        //.padding(start = 8.dp, end = 16.dp)
                        .size(30.dp),
                    imageVector = Icons.Default.ExitToApp ,
                    contentDescription = "Out"
                )
            }

            IconButton(
                onClick = {
                    showDialogAdd = true
                }
            ) {
                Icon(
                    modifier = Modifier
                        .size(30.dp),
                    imageVector = Icons.Default.Add ,
                    contentDescription = "Add publications",
                    )
            }
            if (showDialogAdd) {
                createPublication(
                    onDismiss = { showDialogAdd = false },
                    onSend = { showDialogAdd = false },
                    publicacionRepositorio
                )
            }
        }
    )
}

/******************** PUBLICATIONS ********************/
@Composable
fun publications(
    username: String,
    subject: String,
    description: String
){
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .clip(RoundedCornerShape(19.dp)),
        verticalArrangement = Arrangement.spacedBy(12.dp)
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
                        text = subject
                    )
                    Text(
                        text = description
                    )

                }

            }
            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = Modifier.align(Alignment.Top),
                    onClick = { /*TODO*/ })
                {
                    Icon(
                        modifier = Modifier
                            .size(22.dp),
                        imageVector = Icons.Default.Edit ,
                        contentDescription = "Edit publication"
                    )
                }

                IconButton(
                    modifier = Modifier.align(Alignment.Top),
                    onClick = { /*TODO*/ })
                {
                    Icon(
                        modifier = Modifier
                            .size(22.dp),
                        imageVector = Icons.Default.Delete ,
                        contentDescription = "Edit publication"
                    )
                }
            }
        }
    }
}
