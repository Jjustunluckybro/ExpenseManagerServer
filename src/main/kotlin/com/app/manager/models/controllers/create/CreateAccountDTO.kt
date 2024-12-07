package com.app.manager.models.controllers.create

import com.app.manager.models.enums.AccountType

class CreateAccountDTO(
    val name: String,
    val descriptor: String?,
    val balance: Float,
    val userID: Long,
    val type: AccountType
)
