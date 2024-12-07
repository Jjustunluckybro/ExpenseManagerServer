package com.app.manager.services.components.currency

import com.app.manager.models.enums.Currency

interface ICurrencyConvertor {
    fun calculateCurrencyRate(
        amount: Float,
        currency1: Currency,
        currency2: Currency
    ): Float
}
