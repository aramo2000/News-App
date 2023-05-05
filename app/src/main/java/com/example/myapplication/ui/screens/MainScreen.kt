package com.example.myapplication.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.models.Filter
import com.example.myapplication.ui.components.FilterButton
import com.example.myapplication.ui.components.SearchBar
import com.example.myapplication.ui.components.NewsRefreshableLazyColumn
import com.example.pssexample.models.ArticleResponse
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.material.Text


@Composable
fun MainScreen(navController:  NavHostController,
                news: List<ArticleResponse>,
                onRefresh: () -> Unit,
                onSearch: (String) -> Unit,
                onSelectFilter: (String) -> Unit) {
    Column {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){
            FilterButton(
                filters = listOf(
                    Filter(1, "Business"),
                    Filter(2, "Entertainment"),
                    Filter(3, "General"),
                    Filter(4, "Health"),
                ),
                onFilterSelected = { filter ->
                    onSelectFilter(filter.name)  // API Request with filter
                }
            )

            SearchBar(onSearch = { query ->
                onSearch(query)
                Log.d(">>>", query)
            })
        }
        if (news.isEmpty()) {
            Text(
                "No results found",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Gray,
            )
        }

        NewsRefreshableLazyColumn(
            data = news,
            onRefresh = onRefresh,
            navController = navController
        )
    }
}