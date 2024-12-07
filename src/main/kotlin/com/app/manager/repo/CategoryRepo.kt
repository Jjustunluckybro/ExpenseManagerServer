package com.app.manager.repo

import com.app.manager.models.database.CategoryDB
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepo : JpaRepository<CategoryDB, Long> {
    @Query("""SELECT c FROM CategoryDB c WHERE c.user.id = :id""")
    fun findCategoriesByUserId(id: Long): List<CategoryDB>

    @Query("""SELECT c FROM CategoryDB c WHERE c.id = :id""")
    fun findCategoryById(id: Long): CategoryDB?
}
