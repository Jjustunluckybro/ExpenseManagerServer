package com.app.manager.repo

import com.app.manager.models.database.SubCategoryDB
import com.app.manager.models.dto.CategoryDTO
import com.app.manager.models.dto.SubCategoryDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SubCategoryRepo : JpaRepository<SubCategoryDB, Long> {
    @Query("""SELECT sc FROM SubCategoryDB sc WHERE sc.id = :id""")
    fun findSubCategoryById(id: Long): SubCategoryDB?

    @Query("""SELECT sc FROM SubCategoryDB sc WHERE sc.category.id = :id""")
    fun findSubCategoriesByCategoryId(id: Long): List<SubCategoryDB>

    @Modifying
    @Query(
        """
        UPDATE SubCategoryDB c
        SET c.name = :#{#category.name},
            c.descriptor = :#{#category.descriptor}
        WHERE c.id = :#{#category.id}
    """
    )
    fun updateSubCategory(category: SubCategoryDTO): Int
}
