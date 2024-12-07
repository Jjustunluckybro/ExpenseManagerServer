package com.app.manager.services

import com.app.manager.exception.data.InvalidRelationException
import com.app.manager.exception.data.NotFoundException
import com.app.manager.models.controllers.create.CreateExpenseDTO
import com.app.manager.models.convertors.ExpenseModelsConvertor
import com.app.manager.models.database.AccountDB
import com.app.manager.models.database.CategoryDB
import com.app.manager.models.database.SubCategoryDB
import com.app.manager.models.database.UserDB
import com.app.manager.repo.*
import com.app.manager.services.components.currency.ICurrencyConvertor
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class ExpenseService(
    private val expenseRepo: ExpenseRepo,
    private val userRepo: UserRepo,
    private val accountsRepo: AccountsRepo,
    private val categoryRepo: CategoryRepo,
    private val subCategoryRepo: SubCategoryRepo,
    private val expenseModelsConvertor: ExpenseModelsConvertor,
    private val currencyConvertor: ICurrencyConvertor
) {
    fun createNewExpense(expense: CreateExpenseDTO): Long {
        // Collect data
        val userDB: UserDB = userRepo.findUserById(expense.userId)
            ?: throw NotFoundException("User with id ${expense.userId} not found")
        val accountDB: AccountDB = accountsRepo.findAccountById(expense.accountId)
            ?: throw NotFoundException("Account with id ${expense.accountId} not found")
        val categoryDB: CategoryDB = categoryRepo.findCategoryById(expense.categoryId)
            ?: throw NotFoundException("Category with id ${expense.categoryId} not found")

        var subCategoryDB: SubCategoryDB? = null
        if (expense.subCategoryId != null) {
            subCategoryDB = subCategoryRepo.findSubCategoryById(expense.subCategoryId)
                ?: throw NotFoundException("SubCategory with id ${expense.subCategoryId} not found")
            if (categoryDB.id != subCategoryDB.category.id) {
                throw InvalidRelationException("Category must be parent of SubCategory")
            }
        }

        // Calculation
        val currencyRate: Float? = userDB.settings?.currency?.let {
            currencyConvertor.calculateCurrencyRate(
                amount = expense.amount,
                currency1 = expense.currency,
                currency2 = it
            )
        }
        val userTimezone: Int? = userDB.settings?.timeZone
        if (userTimezone != null) {
            expense.dt = expense.dt.plus(Duration.ofHours(userTimezone.toLong()))
        }

        // Write to database
        return expenseRepo.save(
            expenseModelsConvertor.fromDTO(
                expense = expense,
                user = userDB,
                account = accountDB,
                currencyRate = currencyRate ?: 1f, // Если нет выбранной основной валюты, то делам коэф 1
                category = categoryDB,
                subCategory = subCategoryDB
            )
        ).id
    }
}
