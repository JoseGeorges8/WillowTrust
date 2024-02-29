package com.josegeorges.willowtrust.ui.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josegeorges.willowtrust.R
import com.josegeorges.willowtrust.data.models.transactions.Transaction
import com.josegeorges.willowtrust.data.models.transactions.Recurrent
import com.josegeorges.willowtrust.data.models.transactions.TransactionType
import com.josegeorges.willowtrust.data.models.transactions.previewTransactions
import java.time.format.DateTimeFormatter


@Composable
fun TransactionList(transactions: List<Transaction>, onTransactionLongPressed: (transaction: Transaction) -> Unit) {
    LazyColumn {
        items(transactions.sortedByDescending { it.date }) { transaction ->
            TransactionRow(transaction = transaction, onLongPressed = { onTransactionLongPressed(transaction) })
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TransactionRow(transaction: Transaction, onLongPressed: () -> Unit) {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy")
    val formattedDate = transaction.date.format(dateTimeFormatter)
    val formattedAmount = when (transaction.type) {
        TransactionType.Expense -> "- $${transaction.amount}"
        TransactionType.Income -> "+ $${transaction.amount}"
    }
    val amountColor = when (transaction.type) {
        TransactionType.Expense -> Color.Red
        TransactionType.Income -> Color.Green
    }
    val haptics = LocalHapticFeedback.current
    Column(
        modifier = Modifier
            .combinedClickable(
                onClick = { },
                onLongClick = {
                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                    onLongPressed()
                },
                onLongClickLabel = stringResource(R.string.delete_transaction)
            )
    ) {
        ListItem(
            modifier = Modifier,
            headlineContent = { Text(transaction.institution) },
            supportingContent = { Text(formattedDate) },
            trailingContent = {
                Text(text = formattedAmount, color = amountColor)
            },
            leadingContent = {
                if (transaction is Recurrent) Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Recurrent"
                )
            }
        )
        HorizontalDivider()
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewTransactionList() {
    TransactionList(transactions = previewTransactions, onTransactionLongPressed = {})
}

