package com.app.manager.models.dto

import com.app.manager.models.enums.Currency
import java.time.Instant

data class ExpenseDTO(
    val id: Long?,
    val amount: Float,
    val currencyRate: Float,
    val dt: Instant,
    val currency: Currency,
    val userId: Long,
    val accountId: Long,
    val categoryId: Long,
    val subCategoryId: Long?,
)
