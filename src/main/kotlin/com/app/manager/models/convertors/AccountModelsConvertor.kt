package com.app.manager.models.convertors

import com.app.manager.models.controllers.create.CreateAccountDTO
import com.app.manager.models.database.AccountDB
import com.app.manager.models.database.UserDB
import com.app.manager.models.dto.AccountDTO
import org.springframework.stereotype.Component

@Component
class AccountModelsConvertor {
    fun fromDTO(account: CreateAccountDTO, user: UserDB): AccountDB {
        return AccountDB(
            id = 0,
            user = user,
            name = account.name,
            balance = account.balance,
            descriptor = account.descriptor,
            type = account.type
        )
    }

    fun toDTO(account: AccountDB): AccountDTO {
        return AccountDTO(
            id = account.id,
            userID = account.user.id,
            name = account.name,
            balance = account.balance,
            descriptor = account.descriptor,
            type = account.type
        )
    }

    fun toDTO(accounts: List<AccountDB>): MutableList<AccountDTO> {
        val result: MutableList<AccountDTO> = mutableListOf()
        for (account in accounts) {
            result += toDTO(account)
        }
        return result
    }
}
