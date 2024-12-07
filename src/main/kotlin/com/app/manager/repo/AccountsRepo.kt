package com.app.manager.repo

import com.app.manager.models.database.AccountDB
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface AccountsRepo : JpaRepository<AccountDB, Long> {
    @Query("""SELECT a FROM AccountDB a WHERE a.id = :id""")
    fun findAccountById(id: Long): AccountDB?

    @Query("""SELECT a FROM AccountDB a WHERE a.user.id = :id""")
    fun findAccountsByUserId(id: Long): List<AccountDB>
}
