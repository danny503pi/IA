package com.example.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Style
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Style
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.ui.AppTab

/**
 * Barra de navegación inferior para cambiar fluidamente entre Catálogo, Favoritos y Tarjetas de Repaso.
 */
@Composable
fun BottomNavBar(
    currentTab: AppTab,
    onTabSelected: (AppTab) -> Unit,
    favoriteCount: Int,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier.testTag("bottom_nav_bar"),
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        // Pestaña Catálogo
        NavigationBarItem(
            selected = currentTab == AppTab.CATALOG,
            onClick = { onTabSelected(AppTab.CATALOG) },
            icon = {
                Icon(
                    imageVector = if (currentTab == AppTab.CATALOG) Icons.Filled.MenuBook else Icons.Outlined.MenuBook,
                    contentDescription = "Catálogo"
                )
            },
            label = { Text("Catálogo") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                indicatorColor = MaterialTheme.colorScheme.primaryContainer
            ),
            modifier = Modifier.testTag("nav_catalog")
        )

        // Pestaña Favoritos / Guardadas
        NavigationBarItem(
            selected = currentTab == AppTab.FAVORITES,
            onClick = { onTabSelected(AppTab.FAVORITES) },
            icon = {
                BadgedBox(
                    badge = {
                        if (favoriteCount > 0) {
                            Badge(
                                containerColor = MaterialTheme.colorScheme.secondary,
                                contentColor = MaterialTheme.colorScheme.onSecondary
                            ) {
                                Text("$favoriteCount")
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = if (currentTab == AppTab.FAVORITES) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                        contentDescription = "Favoritos"
                    )
                }
            },
            label = { Text("Favoritos") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                indicatorColor = MaterialTheme.colorScheme.primaryContainer
            ),
            modifier = Modifier.testTag("nav_favorites")
        )

        // Pestaña Tarjetas de Repaso (Flashcards)
        NavigationBarItem(
            selected = currentTab == AppTab.FLASHCARDS,
            onClick = { onTabSelected(AppTab.FLASHCARDS) },
            icon = {
                Icon(
                    imageVector = if (currentTab == AppTab.FLASHCARDS) Icons.Filled.Style else Icons.Outlined.Style,
                    contentDescription = "Tarjetas de Repaso"
                )
            },
            label = { Text("Repaso") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                indicatorColor = MaterialTheme.colorScheme.primaryContainer
            ),
            modifier = Modifier.testTag("nav_flashcards")
        )
    }
}
