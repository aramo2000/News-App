package com.example.myapplication.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.Clear
import androidx.compose.ui.Alignment
import androidx.compose.foundation.clickable



@Composable
fun SearchBar(onSearch: (String) -> Unit) {
    var searchValue by remember { mutableStateOf(TextFieldValue()) }

    Card(
        backgroundColor = Color.White,
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row {
            TextField(
                value = searchValue,
                onValueChange = {
                    searchValue = it
                    //onSearch(it.text)
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearch(searchValue.text)
                    }
                ),
                placeholder = {
                    Text("Search...")
                },
                singleLine = true,
                maxLines = 1,
                leadingIcon = {
                    Button(onClick = { onSearch(searchValue.text) }) {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "Search icon"
                        )
                    }
                },
                trailingIcon = {
                    if (searchValue.text.isNotEmpty()) {
                        Box(
                            Modifier.clickable(onClick = {
                                searchValue = TextFieldValue()
                            }),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Filled.Clear,
                                contentDescription = "Clear search icon",
                                tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                            )
                        }
                    }
                }
            )
        }
    }
}
