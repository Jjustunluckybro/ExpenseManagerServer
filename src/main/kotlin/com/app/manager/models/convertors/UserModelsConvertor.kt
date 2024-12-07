package com.app.manager.models.convertors

import com.app.manager.models.controllers.create.CreateUserControllerDTO
import com.app.manager.models.database.UserDB
import com.app.manager.models.dto.UserDTO
import org.springframework.stereotype.Component

@Component
class UserModelsConvertor {
    fun fromDTO(user: CreateUserControllerDTO): UserDB {
        return UserDB(
            id = user.id,
            name = user.name,
            username = user.username,
            settings = null
        )
    }

    fun toDTO(user: UserDB): UserDTO {
        return UserDTO(
            id = user.id,
            username = user.username,
            name = user.name,
            userSettingsId = user.settings?.id
        )
    }
}
