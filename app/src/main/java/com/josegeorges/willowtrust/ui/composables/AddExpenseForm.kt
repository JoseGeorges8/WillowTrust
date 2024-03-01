package com.josegeorges.willowtrust.ui.composables

import androidx.compose.animation.AnimatedVisibility
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
import com.josegeorges.willowtrust.data.models.transactions.ExpenseCategory
import com.josegeorges.willowtrust.data.models.transactions.Transaction
import com.josegeorges.willowtrust.data.models.transactions.TransactionType
import java.time.LocalDate

@Composable
fun AddExpenseForm(modifier: Modifier, onSaveButtonPressed: (Transaction) -> Unit) {
    var amount by rememberSaveable { mutableStateOf("") }
    var institution by rememberSaveable { mutableStateOf("") }
    var transactionType by rememberSaveable { mutableStateOf(TransactionType.Expense) }
    var expenseCategory by rememberSaveable { mutableStateOf(ExpenseCategory.Entertainment) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val addTransaction = {
        val transaction = Transaction(
            amount = amount.toDouble(),
            institution = institution,
            date = LocalDate.now(),
            type = transactionType
        )
        onSaveButtonPressed(transaction)
    }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            FormDropdownField(
                modifier = Modifier.fillMaxWidth(),
                label = "Type of Transaction",
                value = transactionType.name,
                options = TransactionType.entries,
                onOptionSelected = {
                    transactionType = it
                },
            )
            AnimatedVisibility(visible = transactionType == TransactionType.Expense) {
                FormDropdownField(
                    modifier = Modifier.fillMaxWidth(),
                    label = "Expense Category",
                    value = expenseCategory.name,
                    options = ExpenseCategory.entries,
                    onOptionSelected = {
                        expenseCategory = it
                    },
                )
            }
            FormTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Institution",
                value = institution,
                onValueChanged = { institution = it },
            )
            FormTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Amount",
                value = amount,
                onValueChanged = { amount = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                    addTransaction()
                })
            )
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            onClick = addTransaction
        ) {
            Text("Add")
        }
    }
}


@Preview
@Composable
fun AddExpenseFormPreview() {
    AddExpenseForm(modifier = Modifier, onSaveButtonPressed = {})
}

