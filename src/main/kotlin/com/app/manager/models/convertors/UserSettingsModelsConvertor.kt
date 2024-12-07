package com.app.manager.models.convertors

import com.app.manager.models.controllers.create.CrateUserSettingsDTO
import com.app.manager.models.database.AccountDB
import com.app.manager.models.database.UserDB
import com.app.manager.models.database.UserSettingsDB
import com.app.manager.models.dto.UserSettingsDTO
import org.springframework.stereotype.Component

@Component
class UserSettingsModelsConvertor {
    fun fromDTO(userSettings: CrateUserSettingsDTO, user: UserDB, mainAccount: AccountDB?): UserSettingsDB {
        return UserSettingsDB(
            id = 0,
            user = user,
            mainAccountDB = mainAccount,
            currency = userSettings.currency,
            timeZone = userSettings.timezone,
        )
    }

    fun toDto(userSettings: UserSettingsDB): UserSettingsDTO {
        return UserSettingsDTO(
            id = userSettings.id,
            userId = userSettings.user.id,
            mainAccountId = userSettings.mainAccountDB?.id,
            currency = userSettings.currency,
            timezone = userSettings.timeZone
        )
    }
}
