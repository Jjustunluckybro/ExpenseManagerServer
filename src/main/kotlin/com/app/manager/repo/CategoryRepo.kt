package com.app.manager.repo

import com.app.manager.models.database.CategoryDB
import com.app.manager.models.dto.CategoryDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepo : JpaRepository<CategoryDB, Long> {
    @Query("""SELECT c FROM CategoryDB c WHERE c.user.id = :id""")
    fun findCategoriesByUserId(id: Long): List<CategoryDB>

    @Query("""SELECT c FROM CategoryDB c WHERE c.id = :id""")
    fun findCategoryById(id: Long): CategoryDB?

    @Modifying
    @Query(
        """
        UPDATE CategoryDB c
        SET c.name = :#{#category.name},
            c.descriptor = :#{#category.descriptor}
        WHERE c.id = :#{#category.id}
    """
    )
    fun updateCategory(category: CategoryDTO): Int
}
