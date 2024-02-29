package com.josegeorges.willowtrust.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josegeorges.willowtrust.data.models.transactions.ExpenseCategory
import com.josegeorges.willowtrust.data.models.transactions.Transaction
import com.josegeorges.willowtrust.data.models.transactions.TransactionType
import java.time.LocalDate

@Composable
fun AddExpenseForm(modifier: Modifier, onSaveButtonPressed: (Transaction) -> Unit) {
    var amount by remember { mutableStateOf("") }
    var institution by remember { mutableStateOf("") }
    var transactionType by remember { mutableStateOf(TransactionType.Expense) }
    var expenseCategory by remember { mutableStateOf(ExpenseCategory.Entertainment) }

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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            onClick = {
                val transaction = Transaction(
                    amount = amount.toDouble(),
                    institution = institution,
                    date = LocalDate.now(),
                    type = transactionType
                )
                onSaveButtonPressed(transaction)
            }
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

