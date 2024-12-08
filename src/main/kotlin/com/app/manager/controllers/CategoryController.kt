package com.app.manager.controllers

import com.app.manager.exception.data.NotFoundException
import com.app.manager.models.controllers.create.CreateCategoryDTO
import com.app.manager.models.controllers.create.CreateSubCategoryDTO
import com.app.manager.models.dto.CategoryDTO
import com.app.manager.models.dto.SubCategoryDTO
import com.app.manager.services.CategoryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/userSettings")
class CategoryController(
    private val categoryService: CategoryService
) {
    @PostMapping("/createCategory")
    @ResponseStatus(HttpStatus.CREATED)
    fun createCategory(@RequestBody category: CreateCategoryDTO) {
        categoryService.createNewCategory(category)
    }

    @PostMapping("/createSubCategory")
    @ResponseStatus(HttpStatus.CREATED)
    fun createSubCategory(@RequestBody subCategory: CreateSubCategoryDTO) {
        categoryService.createNewSubCategory(subCategory)
    }

    @GetMapping("/getAllUserCategories")
    @ResponseStatus(HttpStatus.OK)
    fun getAllUserCategories(userId: Long): List<CategoryDTO> {
        return categoryService.getAllUserCategories(userId)
    }

    @GetMapping("/getAllSubCategoriesByParentCategoryID")
    @ResponseStatus(HttpStatus.OK)
    fun getAllSubCategoriesByParentCategoryID(categoryId: Long): List<SubCategoryDTO> {
        return categoryService.getAllSubCategoryByCategoryId(categoryId)
    }

    @PatchMapping("/updateCategory")
    @ResponseStatus(HttpStatus.OK)
    fun updateCategory(
        @RequestParam categoryId: Long,
        @RequestParam newName: String?,
        @RequestParam newDescription: String?
    ) {
        categoryService.updateCategory(categoryId, newName, newDescription)
    }

    @PatchMapping("/updateSubCategory")
    @ResponseStatus(HttpStatus.OK)
    fun updateSubCategory(
        @RequestParam subCategoryId: Long,
        @RequestParam newName: String?,
        @RequestParam newDescription: String?
    ) {
        categoryService.updateSubCategory(subCategoryId, newName, newDescription)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e: NotFoundException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
    }
}
