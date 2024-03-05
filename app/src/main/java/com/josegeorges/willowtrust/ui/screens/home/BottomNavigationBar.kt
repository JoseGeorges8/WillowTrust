package com.josegeorges.willowtrust.ui.screens.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.josegeorges.willowtrust.ui.screens.Screens

@Composable
fun BottomNavigationBar(navController: NavController) {
    //initializing the default selected item
    var navigationSelectedItem by remember {
        mutableIntStateOf(0)
    }

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route


        val home = BottomNavigationItem(
            label = "Home",
            icon = Icons.Filled.Home,
            route = Screens.Account.route
        )
        val expenses = BottomNavigationItem(
            label = "Expenses",
            icon = Icons.Filled.DateRange,
            route = Screens.Budget.route
        )

        NavigationBarItem(
            selected = 0 == navigationSelectedItem,
            label = {
                Text(home.label)
            },
            icon = {
                Icon(
                    home.icon,
                    contentDescription = home.label
                )
            },
            onClick = {
                navigationSelectedItem = 0
                navController.navigate(home.route) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    // Avoid multiple copies of the same destination when
                    // re-selecting the same item
                    launchSingleTop = true
                    // Restore state when re-selecting a previously selected item
                    restoreState = true
                }
            }
        )
        FloatingActionButton(onClick = { }) {
            Icon(Icons.Default.Add, contentDescription = "Add")
        }
        NavigationBarItem(
            selected = 1 == navigationSelectedItem,
            label = {
                Text(expenses.label)
            },
            icon = {
                Icon(
                    expenses.icon,
                    contentDescription = expenses.label
                )
            },
            onClick = {
                navigationSelectedItem = 1
                navController.navigate(expenses.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}