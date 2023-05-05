package com.example.myapplication.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.pssexample.models.ArticleResponse
import com.example.myapplication.AllScreens
import androidx.compose.material.MaterialTheme

@Composable
fun NewsList(navController: NavController, news: List<ArticleResponse>) {

    LazyColumn {
        itemsIndexed(news) { index, neww ->
            NewsCard(neww = neww) {
                navController.navigate(AllScreens.DetailScreen.route + "/$index")
            }
        }
    }
}

@Composable
private fun NewsCard(neww: ArticleResponse, onClick: () -> Unit) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = onClick)
            .clip(shape = RoundedCornerShape(10.dp))
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = neww.urlToImage),
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp).width(100.dp)
                    .fillMaxWidth()
            )
            Column {
                Text(text = neww.title, style = MaterialTheme.typography.h5)
                neww.author?.let { Text(text = it, style = MaterialTheme.typography.body1) }
            }
        }
    }
}