package com.josegeorges.willowtrust.ui.screens.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.josegeorges.willowtrust.ui.screens.Screens

//initializing the data class with default parameters
data class BottomNavigationItem(
    val label : String = "",
    val icon : ImageVector = Icons.Filled.Home,
    val route : String = ""
) {
    companion object {
        //function to get the list of bottomNavigationItems
        val bottomNavigationItems = listOf(
            BottomNavigationItem(
                label = "Home",
                icon = Icons.Filled.Home,
                route = Screens.Account.route
            ),
            BottomNavigationItem(
                label = "Expenses",
                icon = Icons.Filled.DateRange,
                route = Screens.Budget.route
            ),
        )
    }
}