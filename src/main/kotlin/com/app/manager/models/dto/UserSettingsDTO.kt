package com.app.manager.models.dto

import com.app.manager.models.enums.Currency

data class UserSettingsDTO(
    val id: Long,
    val userId: Long,
    val mainAccountId: Long?,
    val currency: Currency,
    val timezone: Int
)
