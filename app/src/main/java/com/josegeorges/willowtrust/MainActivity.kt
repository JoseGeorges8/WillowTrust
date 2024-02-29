package com.josegeorges.willowtrust

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.josegeorges.willowtrust.ui.WillowTrustApp
import com.josegeorges.willowtrust.ui.theme.WillowTrustTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WillowTrustTheme {
                WillowTrustApp()
            }
        }
    }
}

