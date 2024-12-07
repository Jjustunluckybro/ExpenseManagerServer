package com.app.manager.controllers

import com.app.manager.exception.data.InvalidRelationException
import com.app.manager.exception.data.NotFoundException
import com.app.manager.models.controllers.create.CreateExpenseDTO
import com.app.manager.services.ExpenseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/userSettings")
class ExpenseController(
    private val expenseService: ExpenseService
) {
    @PostMapping("/createExpense")
    @ResponseStatus(HttpStatus.CREATED)
    fun createExpense(@RequestBody expense: CreateExpenseDTO) {
        expenseService.createNewExpense(expense)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e: NotFoundException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
    }

    @ExceptionHandler(InvalidRelationException::class)
    fun handleNotFoundException(e: InvalidRelationException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
    }
}
