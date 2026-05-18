package com.example.luminary.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.luminary.feature.bookmarks.BookmarksScreen
import com.example.luminary.feature.detail.DetailScreen
import com.example.luminary.feature.discover.DiscoverScreen
import com.example.luminary.feature.home.HomeScreen

private data class TopLevelDestination(
    val route: Any,
    val label: String,
    val selectedIcon: @Composable () -> Unit,
    val unselectedIcon: @Composable () -> Unit
)

private val TOP_LEVEL_DESTINATIONS = listOf(
    TopLevelDestination(
        route = HomeRoute,
        label = "Home",
        selectedIcon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
        unselectedIcon = { Icon(Icons.Outlined.Home, contentDescription = "Home") }
    ),
    TopLevelDestination(
        route = DiscoverRoute,
        label = "Discover",
        selectedIcon = { Icon(Icons.Filled.Explore, contentDescription = "Discover") },
        unselectedIcon = { Icon(Icons.Outlined.Explore, contentDescription = "Discover") }
    ),
    TopLevelDestination(
        route = BookmarksRoute,
        label = "Bookmarks",
        selectedIcon = { Icon(Icons.Filled.Bookmark, contentDescription = "Bookmarks") },
        unselectedIcon = { Icon(Icons.Outlined.BookmarkBorder, contentDescription = "Bookmarks") }
    )
)

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination

    val showBottomBar = TOP_LEVEL_DESTINATIONS.any { dest ->
        currentDestination?.hierarchy?.any { it.hasRoute(dest.route::class) } == true
    }

    SharedTransitionLayout {
        Scaffold(
            bottomBar = {
                if (showBottomBar) {
                    NavigationBar {
                        TOP_LEVEL_DESTINATIONS.forEach { destination ->
                            val selected = currentDestination?.hierarchy?.any {
                                it.hasRoute(destination.route::class)
                            } == true

                            NavigationBarItem(
                                selected = selected,
                                onClick = {
                                    navController.navigate(destination.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                icon = {
                                    if (selected) destination.selectedIcon()
                                    else destination.unselectedIcon()
                                },
                                label = { Text(destination.label) }
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = HomeRoute,
                modifier = Modifier.padding(innerPadding),
                enterTransition = {
                    fadeIn(tween(300)) + slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Start,
                        tween(300)
                    )
                },
                exitTransition = {
                    fadeOut(tween(200)) + slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Start,
                        tween(300)
                    )
                },
                popEnterTransition = {
                    fadeIn(tween(300)) + slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.End,
                        tween(300)
                    )
                },
                popExitTransition = {
                    fadeOut(tween(200)) + slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.End,
                        tween(300)
                    )
                }
            ) {
                composable<HomeRoute>(
                    enterTransition = { fadeIn(tween(300)) },
                    exitTransition = { fadeOut(tween(200)) },
                    popEnterTransition = { fadeIn(tween(300)) },
                    popExitTransition = { fadeOut(tween(200)) }
                ) {
                    HomeScreen(
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedVisibilityScope = this@composable,
                        onArticleClick = { articleId ->
                            navController.navigate(ArticleDetailRoute(articleId))
                        }
                    )
                }

                composable<DiscoverRoute>(
                    enterTransition = { fadeIn(tween(300)) },
                    exitTransition = { fadeOut(tween(200)) },
                    popEnterTransition = { fadeIn(tween(300)) },
                    popExitTransition = { fadeOut(tween(200)) }
                ) {
                    DiscoverScreen(
                        onArticleClick = { articleId ->
                            navController.navigate(ArticleDetailRoute(articleId))
                        }
                    )
                }

                composable<BookmarksRoute>(
                    enterTransition = { fadeIn(tween(300)) },
                    exitTransition = { fadeOut(tween(200)) },
                    popEnterTransition = { fadeIn(tween(300)) },
                    popExitTransition = { fadeOut(tween(200)) }
                ) {
                    BookmarksScreen(
                        onDiscoverClick = {
                            navController.navigate(DiscoverRoute) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }

                composable<ArticleDetailRoute> {
                    DetailScreen(
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedVisibilityScope = this@composable,
                        onBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}
