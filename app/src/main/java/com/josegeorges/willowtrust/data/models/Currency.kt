package com.josegeorges.willowtrust.data.models

import com.josegeorges.willowtrust.R

enum class Currency(val symbol: String, val resourceId: Int) {
    usd("$", R.string.currency_usd),
    cad("$", R.string.currency_cad),
}