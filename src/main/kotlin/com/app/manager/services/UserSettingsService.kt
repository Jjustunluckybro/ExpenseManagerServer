package com.app.manager.services

import com.app.manager.exception.data.NotFoundException
import com.app.manager.models.controllers.create.CrateUserSettingsDTO
import com.app.manager.models.convertors.UserSettingsModelsConvertor
import com.app.manager.models.database.UserDB
import com.app.manager.models.database.UserSettingsDB
import com.app.manager.models.dto.UserSettingsDTO
import com.app.manager.repo.UserRepo
import com.app.manager.repo.UserSettingsRepo
import org.springframework.stereotype.Service

@Service
class UserSettingsService (
    private val userSettingsRepo: UserSettingsRepo,
    private val userRepo: UserRepo,
    private val userSettingsModelsConvertor: UserSettingsModelsConvertor
){
    fun createNewUserSettings(userSettings: CrateUserSettingsDTO): Long {
        val userDB: UserDB = userRepo.findUserById(userSettings.userId)
            ?: throw NotFoundException("User with id ${userSettings.userId} not found")

        val userSettingsToSave: UserSettingsDB = userSettingsModelsConvertor.fromDTO(
            userSettings = userSettings,
            user = userDB,
            mainAccount = null
        )
        return userSettingsRepo.save(userSettingsToSave).id
    }

    fun getSettingsByUserId(userId: Long): UserSettingsDTO {
        return userSettingsRepo.findUserSettingsByUserId(userId)
            ?.let { userSettingsModelsConvertor.toDto(it) }
            ?: throw NotFoundException("Settings attempt to user with id $userId not found")
    }
}
