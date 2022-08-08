package com.example.tipcalculator.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    onclick: () -> Unit = {}
) {
    Card(modifier = modifier
        .size(45.dp)
        .padding(3.dp)
        .clickable {
            onclick()
        },
        shape = CircleShape,
        backgroundColor = Color(0xFFF387E1),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "add or remove",
            modifier = Modifier.background(MaterialTheme.colors.onBackground)
        )
    }
}