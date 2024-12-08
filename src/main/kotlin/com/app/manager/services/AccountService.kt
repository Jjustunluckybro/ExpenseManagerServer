package com.app.manager.services

import com.app.manager.exception.data.NotFoundException
import com.app.manager.models.controllers.create.CreateAccountDTO
import com.app.manager.models.convertors.AccountModelsConvertor
import com.app.manager.models.database.AccountDB
import com.app.manager.models.database.UserDB
import com.app.manager.models.dto.AccountDTO
import com.app.manager.models.enums.AccountType
import com.app.manager.repo.AccountsRepo
import com.app.manager.repo.UserRepo
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountsRepo: AccountsRepo,
    private val userRepo: UserRepo,
    private val accountModelsConvertor: AccountModelsConvertor
) {
    fun createNewAccount(account: CreateAccountDTO): Long {
        val userDB: UserDB = userRepo.findUserById(account.userID)
            ?: throw NotFoundException("User with id ${account.userID} not found")

        val accountToSave: AccountDB = accountModelsConvertor.fromDTO(account, userDB)
        return accountsRepo.save(accountToSave).id
    }

    fun getAllUserAccounts(userId: Long): List<AccountDTO> {
        return accountModelsConvertor.toDTO(accountsRepo.findAccountsByUserId(userId))
    }

    @Transactional
    fun updateAccount(
        accountId: Long,
        newName: String?,
        newType: AccountType?,
        newBalance: Float?,
        newDescription: String?
    ) {
        val accountDB: AccountDB = accountsRepo.findAccountById(accountId)
            ?: throw NotFoundException("Account with id $accountId not found")
        val account = AccountDTO(
            id = accountId,
            userID = accountDB.user.id,
            name = newName ?: accountDB.name,
            type = newType ?: accountDB.type,
            balance = newBalance ?: accountDB.balance,
            descriptor = newDescription ?: accountDB.descriptor,
        )
        accountsRepo.updateAccount(account)
    }
}
