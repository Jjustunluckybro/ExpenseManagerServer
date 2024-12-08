package com.app.manager.models.convertors

import com.app.manager.models.controllers.create.CreateExpenseDTO
import com.app.manager.models.database.*
import com.app.manager.models.dto.ExpenseDTO
import org.springframework.stereotype.Component

@Component
class ExpenseModelsConvertor {
    fun fromDTO(
        expense: CreateExpenseDTO,
        currencyRate: Float,
        user: UserDB,
        account: AccountDB,
        category: CategoryDB,
        subCategory: SubCategoryDB? = null
    ): ExpenseDB {
        return ExpenseDB(
            id = 0,
            amount = expense.amount,
            currencyRate = currencyRate,
            dt = expense.dt,
            currency = expense.currency,
            user = user,
            account = account,
            category = category,
            subCategory = subCategory,
        )
    }

    fun toDTO(expense: ExpenseDB): ExpenseDTO {
        return ExpenseDTO(
            id = expense.id,
            amount = expense.amount,
            currencyRate = expense.currencyRate,
            dt = expense.dt,
            currency = expense.currency,
            userId = expense.user.id,
            accountId = expense.account.id,
            categoryId = expense.category.id,
            subCategoryId = expense.subCategory?.id
        )
    }

    fun toDTO(expenses: List<ExpenseDB>): List<ExpenseDTO> {
        return buildList {
            for (expense in expenses) {
                val dto = toDTO(expense)
                add(dto)
            }
        }
    }
}
