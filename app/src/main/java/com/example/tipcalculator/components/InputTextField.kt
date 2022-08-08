package com.example.tipcalculator.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun inputfield(
    lableid: String,
    valuestate: MutableState<String>,
    enabled: Boolean,
    singleline: Boolean,
    keyboardtype: KeyboardType,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions
) {


    OutlinedTextField(
        value = valuestate.value,
        onValueChange = {
            valuestate.value = it
        }, modifier = Modifier
            .padding(10.dp, 20.dp)
            .fillMaxWidth(), enabled = enabled,
        label = {
            Text(text = lableid)
        }, leadingIcon = {
            Icon(imageVector = Icons.Rounded.AttachMoney, contentDescription = "money")
        }, singleLine = singleline,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.onBackground),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardtype, imeAction = imeAction),
        keyboardActions = onAction

    )
}