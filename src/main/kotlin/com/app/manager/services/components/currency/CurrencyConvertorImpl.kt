package com.app.manager.services.components.currency

import com.app.manager.models.enums.Currency
import org.springframework.stereotype.Component

@Component
class CurrencyConvertorImpl : ICurrencyConvertor {
    override fun calculateCurrencyRate(
        amount: Float,
        currency1: Currency,
        currency2: Currency
    ): Float {
        return 0f; // TODO
    }
}
