package com.josegeorges.willowtrust.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josegeorges.willowtrust.data.models.budget.Budget

@Composable
fun CreateBudgetForm(modifier: Modifier, onCreateButtonPressed: (Budget) -> Unit) {
    var name by rememberSaveable { mutableStateOf("") }
    var limit by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val createBudget = {
        val budget = Budget(
            limit = limit.toDouble(),
            name = name,
        )
        onCreateButtonPressed(budget)
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            FormTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Name of the budget",
                value = name,
                onValueChanged = { name = it },
            )
            FormTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Limit",
                value = limit,
                onValueChanged = { limit = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                    createBudget()
                })
            )
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            onClick = createBudget
        ) {
            Text("Add")
        }
    }
}


@Preview
@Composable
fun PreviewCreateBudgetForm() {
    CreateBudgetForm(modifier = Modifier, onCreateButtonPressed = {})
}

