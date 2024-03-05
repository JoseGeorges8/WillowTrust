package com.josegeorges.willowtrust.data.dataSources.settings

import com.josegeorges.willowtrust.data.models.Currency

interface SettingsDataSource {
    suspend fun getPreferredCurrency() : Currency
    suspend fun setPreferredCurrency(currency: Currency)
}