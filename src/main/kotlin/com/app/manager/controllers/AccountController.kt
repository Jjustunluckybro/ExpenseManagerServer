package com.app.manager.controllers

import com.app.manager.exception.data.NotFoundException
import com.app.manager.models.controllers.create.CreateAccountDTO
import com.app.manager.models.dto.AccountDTO
import com.app.manager.services.AccountService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/userSettings")
class AccountController (
    private val accountService: AccountService
){
    @PostMapping("/createAccount")
    @ResponseStatus(HttpStatus.CREATED)
    fun createAccount(@RequestBody account: CreateAccountDTO) {
        accountService.createNewAccount(account)
    }

    @GetMapping("/getAllUserAccounts")
    @ResponseStatus(HttpStatus.OK)
    fun getAllUserAccounts(userId: Long): List<AccountDTO> {
        return accountService.getAllUserAccounts(userId)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e: NotFoundException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
    }
}
