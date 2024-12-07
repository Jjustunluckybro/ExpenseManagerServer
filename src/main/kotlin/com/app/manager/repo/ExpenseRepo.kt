package com.app.manager.repo

import com.app.manager.models.database.ExpenseDB
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExpenseRepo : JpaRepository<ExpenseDB, Long>
