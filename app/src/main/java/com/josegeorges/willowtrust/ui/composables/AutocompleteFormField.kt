package com.josegeorges.willowtrust.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutocompleteFormField(
    items: List<String>,
    onValueChanged: (String) -> Unit,
    selectedItem: String,
    label: String,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    var listItems by remember(items, selectedItem) {
        mutableStateOf(
            if (selectedItem.isNotEmpty()) {
                items.filter { x -> x.startsWith(selectedItem.lowercase(), ignoreCase = true) }
            } else {
                items.toList()
            }
        )
    }
    var selectedText by remember(selectedItem) { mutableStateOf(selectedItem) }

    LaunchedEffect(selectedItem){
        selectedText = selectedItem
    }

    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = selectedText,
                label = { Text(label) },
                onValueChange = {
                    if (!expanded) {
                        expanded = true
                    }
                    selectedText = it
                    listItems = if (it.isNotEmpty()) {
                        items.filter { x -> x.startsWith(it.lowercase(), ignoreCase = true) }
                    } else {
                        items.toList()
                    }
                    onValueChanged(it)
                },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                if (listItems.isEmpty()) {
                    DropdownMenuItem(
                        text = { Text(text = "No items found") },
                        onClick = {
                            expanded = false
                        }
                    )
                } else {
                    listItems.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                selectedText = item
                                expanded = false
                                onValueChanged(item)
                            }
                        )
                    }
                }
            }
        }
    }
}