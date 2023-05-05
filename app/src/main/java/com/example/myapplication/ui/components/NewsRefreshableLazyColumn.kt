package com.example.myapplication.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.pssexample.models.ArticleResponse
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun NewsRefreshableLazyColumn(
    data: List<ArticleResponse>,
    onRefresh: () -> Unit,
    navController: NavController,
) {
    val state = rememberSwipeRefreshState(isRefreshing = false)

    SwipeRefresh(
        state = state,
        onRefresh = {
            onRefresh()
            state.isRefreshing = false
        }
    ) {
        NewsList(navController, data)
    }
}