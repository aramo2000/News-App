package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.livedata.observeAsState
import com.example.pssexample.models.NewsResponse
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import com.example.myapplication.ui.theme.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import retrofit2.Response
import androidx.compose.foundation.Image
import coil.compose.rememberImagePainter
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment



class MainActivity : ComponentActivity() {
    private val viewModel: DataLoaderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val postsResult = viewModel.articless.observeAsState(Result.loading())
                    Column {
                        Text(
                            text = "News",
                            style = MaterialTheme.typography.h4,
                            modifier = Modifier.padding(16.dp)
                        )
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextField(
                                value = "",
                                onValueChange = { /* TODO */ },
                                placeholder = { Text(text = "Search news") },
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 16.dp, end = 8.dp)
                            )
                            Button(
                                onClick = { /* TODO */ },
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Text(text = "Search")
                            }
                            Button(
                                onClick = { /* TODO */ },
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Text(text = "Filters")
                            }
                        }

                        Button(onClick = { viewModel.loadNews() }) {
                            Text(text = "Get Posts")
                        }
                        PostList(postsResult.value)
                    }
                }
            }
        }
    }
}

@Composable
fun PostList(postsResult: Result<Response<NewsResponse>>) {
    when (postsResult) {
        is Result.Success -> {
            val posts = postsResult.data.body()?.articles.orEmpty()
            LazyColumn {
                items(posts) { post ->
                    Card(
                        Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Column(
                            Modifier.padding(8.dp)
                        ) {
                            Image(
                                painter = rememberImagePainter(data = post.urlToImage),
                                contentDescription = null,
                                modifier = Modifier
                                    .height(200.dp)
                                    .fillMaxWidth()
                            )
                            Text(text = post.title, style = MaterialTheme.typography.h5)
                            post.author?.let { Text(text = it, style = MaterialTheme.typography.body1) }
                        }
                    }
                }
            }
        }
        is Result.Error -> {
            Text(text = "Error: ${postsResult.exception.message}")
        }
        else -> {

        }
    }
}