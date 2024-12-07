package com.app.manager.services

import com.app.manager.exception.data.AlreadyExistException
import com.app.manager.exception.data.NotFoundException
import com.app.manager.models.controllers.create.CreateUserControllerDTO
import com.app.manager.models.convertors.UserModelsConvertor
import com.app.manager.models.dto.UserDTO
import com.app.manager.repo.UserRepo
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service


@Service
class UserService (
    private val userRepo: UserRepo,
    private val userModelsConvertor: UserModelsConvertor
) {
    fun createNewUser(user: CreateUserControllerDTO) {
        try {
            userRepo.saveAndFlush(userModelsConvertor.fromDTO(user))
        } catch (e: DataIntegrityViolationException) {
            throw AlreadyExistException("User with id ${user.id} already exist")
        }
    }

    fun getUserById(userId: Long): UserDTO {
        return userRepo.findUserById(userId)
            ?.let { userModelsConvertor.toDTO(it) }
            ?: throw NotFoundException("User with id $userId not found")
    }
}
