package com.app.manager.controllers

import com.app.manager.exception.data.AlreadyExistException
import com.app.manager.exception.data.NotFoundException
import com.app.manager.models.controllers.create.CreateUserControllerDTO
import com.app.manager.models.dto.UserDTO
import com.app.manager.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService
) {
    @PostMapping("/createUser")
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody user: CreateUserControllerDTO) {
        userService.createNewUser(user)
    }

    @GetMapping("/getUserById")
    @ResponseStatus(HttpStatus.OK)
    fun getUserById(id: Long): UserDTO {
        return userService.getUserById(id)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e: NotFoundException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
    }

    @ExceptionHandler(AlreadyExistException::class)
    fun handleAlreadyExistException(e: AlreadyExistException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.message)
    }
}
