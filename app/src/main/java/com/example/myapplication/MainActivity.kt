package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.myapplication.ui.theme.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.material.MaterialTheme
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.ui.screens.NewsDetailsScreen
import com.example.myapplication.ui.screens.MainScreen


class MainActivity : ComponentActivity() {
    private val viewModel: DataLoaderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadNews()
        viewModel.articless.observe(this) { news ->
            setContent {
                MyApplicationTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = AllScreens.HomeScreen.route
                        ) {
                            // Home Screen
                            when (news) {
                                is Result.Success -> {
                                    val news = news.data.body()?.articles.orEmpty()

                                    composable(route = AllScreens.HomeScreen.route) {
                                        MainScreen(
                                            navController = navController,
                                            news = news,
                                            onRefresh = viewModel::loadNews,
                                            onSearch = {
                                                viewModel.loadNewsWithSearch(it)
                                            },
                                            onSelectFilter = {
                                                viewModel.loadNewsWithCategories(it)
                                            }
                                        )
                                    }

                                    // Details Screen
                                    composable(
                                        route = AllScreens.DetailScreen.route + "/{index}",
                                        arguments = listOf(navArgument(name = "index") {
                                            type = NavType.IntType
                                        })
                                    ) { entry ->
                                        val index = entry.arguments?.getInt("index")

                                        index?.let {
                                            news[it]
                                        }?.let {
                                            NewsDetailsScreen(navController, it)
                                        }
                                    }
                                }
                                else -> {}
                            }
                        }
                    }
                }
            }
        }
    }
}