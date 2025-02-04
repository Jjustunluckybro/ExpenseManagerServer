package com.app.manager.services

import com.app.manager.exception.data.NotFoundException
import com.app.manager.models.controllers.create.CreateCategoryDTO
import com.app.manager.models.controllers.create.CreateSubCategoryDTO
import com.app.manager.models.convertors.CategoryModelsConvertor
import com.app.manager.models.database.CategoryDB
import com.app.manager.models.database.SubCategoryDB
import com.app.manager.models.database.UserDB
import com.app.manager.models.dto.CategoryDTO
import com.app.manager.models.dto.SubCategoryDTO
import com.app.manager.repo.CategoryRepo
import com.app.manager.repo.SubCategoryRepo
import com.app.manager.repo.UserRepo
import jakarta.transaction.Transactional
import org.aspectj.weaver.ast.Not
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val userRepo: UserRepo,
    private val categoryRepo: CategoryRepo,
    private val subCategoryRepo: SubCategoryRepo,
    private val categoryModelsConvertor: CategoryModelsConvertor
) {
    fun createNewCategory(category: CreateCategoryDTO): Long {
        val userDB: UserDB = userRepo.findUserById(category.userId)
            ?: throw NotFoundException("User with id ${category.userId} not found")

        return categoryRepo.save(
            categoryModelsConvertor.fromDTO(category, userDB)
        ).id
    }

    fun createNewSubCategory(subCategory: CreateSubCategoryDTO): Long {
        val categoryDB: CategoryDB = categoryRepo.findCategoryById(subCategory.categoryId)
            ?: throw NotFoundException("Parent category with id ${subCategory.categoryId} not found")

        return subCategoryRepo.save(
            categoryModelsConvertor.fromDTO(subCategory, categoryDB)
        ).id
    }

    fun getAllUserCategories(userId: Long): List<CategoryDTO> {
        return categoryModelsConvertor.categoriesToDTO(
            categoryRepo.findCategoriesByUserId(userId)
        )
    }

    fun getAllSubCategoryByCategoryId(categoryId: Long): List<SubCategoryDTO> {
        return categoryModelsConvertor.subCategoriesToDTO(
            subCategoryRepo.findSubCategoriesByCategoryId(categoryId)
        )
    }

    @Transactional
    fun updateCategory(categoryId: Long, newName: String?, newDescription: String?) {
        val categoryDB: CategoryDB = categoryRepo.findCategoryById(categoryId)
            ?: throw NotFoundException("Category with id $categoryId not found")
        val category = CategoryDTO(
            id = categoryId,
            userId = categoryDB.user.id,
            name = newName ?: categoryDB.name,
            descriptor = newDescription ?: categoryDB.descriptor
        )
        categoryRepo.updateCategory(category)
    }

    @Transactional
    fun updateSubCategory(subCategoryId: Long, newName: String?, newDescription: String?) {
        val subCategoryDB: SubCategoryDB = subCategoryRepo.findSubCategoryById(subCategoryId)
            ?: throw NotFoundException("SubCategory with id $subCategoryId not found")
        val subCategory = SubCategoryDTO(
            id = subCategoryId,
            categoryId = subCategoryDB.category.id,
            name = newName ?: subCategoryDB.name,
            descriptor = newDescription ?: subCategoryDB.descriptor
        )
        subCategoryRepo.updateSubCategory(subCategory)
    }
}
