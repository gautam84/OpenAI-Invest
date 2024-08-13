package com.openaiinvest.app.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpenAIInvestTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    imageVector: ImageVector?,
    placeHolderText: String,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,

    ) {
    OutlinedTextField(
        value = text,
        modifier = modifier.fillMaxWidth(0.8f),
        readOnly = readOnly,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(32.dp),
        leadingIcon = {
            if (imageVector != null) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = null,
                    tint = Color.Black.copy(0.5f)
                )
            }
        },
        keyboardOptions = keyboardOptions,
        placeholder = {
            Text(text = placeHolderText, color = Color.Black.copy(0.5f))
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black.copy(0.5f),
            unfocusedBorderColor = Color.Black.copy(0.5f)
        ),
        singleLine = true

    )
}
