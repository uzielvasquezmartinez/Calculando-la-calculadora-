package com.example.myapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ItemList(
    items: List<String>,
    onItemClick: (String) -> Unit
) {
    Column {
        items.forEach { item ->
            Text(
                text = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onItemClick(item) },
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}