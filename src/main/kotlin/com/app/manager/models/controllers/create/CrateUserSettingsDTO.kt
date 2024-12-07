package com.app.manager.models.controllers.create

import com.app.manager.models.enums.Currency

data class CrateUserSettingsDTO(
    val userId: Long,
    val mainAccountId: Long?,
    val currency: Currency,
    val timezone: Int
)
