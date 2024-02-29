package com.josegeorges.willowtrust.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FormTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
    keyboardActions: KeyboardActions = KeyboardActions()
) {
    TextField(
        modifier = modifier,
        label = { Text(text = label) },
        value = value, onValueChange = onValueChanged,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> FormDropdownField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onOptionSelected: (T) -> Unit,
    options: List<T>,
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            TextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                value = value,
                onValueChange = {},
                label = { Text(label) },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                options.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item.toString()) },
                        onClick = {
                            onOptionSelected(item)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun FormTextFieldPreview() {
    FormTextField(label = "Test", value = "", onValueChanged = {})
}

@Preview
@Composable
fun FormDropdownFieldPreview() {
    FormDropdownField(
        label = "Test",
        value = "",
        onOptionSelected = {},
        options = listOf("Berries", "Bananas", "Peaches"),
    )
}
