package com.app.manager.models.dto

import com.app.manager.models.enums.AccountType


data class AccountDTO(
    val id: Long,
    val name: String,
    val descriptor: String?,
    val balance: Float,
    val type: AccountType,
    val userID: Long
)
