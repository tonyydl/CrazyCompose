package com.tonyydl.crazycompose.ui

import android.content.pm.ActivityInfo
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun OrientationLockDemoApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                listOf("screenA", "screenB", "screenC").forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(getIconForScreen(screen), contentDescription = null) },
                        label = { Text(screen.replace("screen", "")) },
                        selected = currentRoute == screen,
                        onClick = {
                            navController.navigate(screen) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "screenA",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("screenA") {
                ComposableA()
            }
            composable("screenB") {
                OrientationWrapper(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                    ComposableB()
                }
            }
            composable("screenC") {
                ComposableC()
            }
        }
    }
}

@Composable
fun getIconForScreen(screen: String) = when(screen) {
    "screenA" -> Icons.Default.Home
    "screenB" -> Icons.Default.Favorite
    "screenC" -> Icons.Default.Settings
    else -> Icons.Default.Home
}

@Composable
fun ComposableA() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red.copy(alpha = 0.3f)),
        contentAlignment = Alignment.Center
    ) {
        Text("Composable A", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun ComposableB() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green.copy(alpha = 0.3f)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Composable B", style = MaterialTheme.typography.headlineMedium)
        Text("(Portrait Only)", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text("This composable is locked to portrait orientation.")
    }
}

@Composable
fun ComposableC() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue.copy(alpha = 0.3f)),
        contentAlignment = Alignment.Center
    ) {
        Text("Composable C", style = MaterialTheme.typography.headlineMedium)
    }
}

