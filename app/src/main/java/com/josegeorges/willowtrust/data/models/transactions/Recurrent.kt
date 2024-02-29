package com.josegeorges.willowtrust.data.models.transactions

import java.time.LocalDate

interface Recurrent {
    val recurrence: Recurrence
    val startDate: LocalDate
}