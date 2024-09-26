package com.tonyydl.crazycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.tonyydl.crazycompose.ui.OrientationLockDemoApp
import com.tonyydl.crazycompose.ui.theme.CrazyComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CrazyComposeTheme {
                OrientationLockDemoApp()
            }
        }
    }
}
