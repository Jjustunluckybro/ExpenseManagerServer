package com.app.manager.models.controllers.create

import com.app.manager.models.enums.Currency
import java.time.Instant

data class CreateExpenseDTO(
    val amount: Float,
    var dt: Instant,
    val currency: Currency,
    val userId: Long,
    val accountId: Long,
    val categoryId: Long,
    val subCategoryId: Long?,
)
