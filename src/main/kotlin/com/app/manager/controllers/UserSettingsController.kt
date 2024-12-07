package com.app.manager.controllers

import com.app.manager.exception.data.NotFoundException
import com.app.manager.models.controllers.create.CrateUserSettingsDTO
import com.app.manager.models.dto.UserSettingsDTO
import com.app.manager.services.UserSettingsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/userSettings")
class UserSettingsController (
    private val userSettingsService: UserSettingsService
){
    @PostMapping("/createUserSettings")
    @ResponseStatus(HttpStatus.CREATED)
    fun createUserSettings(@RequestBody userSettings: CrateUserSettingsDTO) {
        userSettingsService.createNewUserSettings(userSettings)
    }

    @GetMapping("/getUserSettings")
    @ResponseStatus(HttpStatus.OK)
    fun getSettingsByUserId(userId: Long): UserSettingsDTO {
        return userSettingsService.getSettingsByUserId(userId)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e: NotFoundException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
    }
}
