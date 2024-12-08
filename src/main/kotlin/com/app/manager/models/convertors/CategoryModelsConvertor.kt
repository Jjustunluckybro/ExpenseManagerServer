package com.app.manager.models.convertors

import com.app.manager.models.controllers.create.CreateCategoryDTO
import com.app.manager.models.controllers.create.CreateSubCategoryDTO
import com.app.manager.models.database.CategoryDB
import com.app.manager.models.database.SubCategoryDB
import com.app.manager.models.database.UserDB
import com.app.manager.models.dto.CategoryDTO
import com.app.manager.models.dto.SubCategoryDTO
import org.springframework.stereotype.Component

@Component
class CategoryModelsConvertor {
    fun fromDTO(category: CreateCategoryDTO, user: UserDB): CategoryDB {
        return CategoryDB(
            id = 0,
            name = category.name,
            descriptor = category.descriptor,
            user = user
        )
    }

    fun fromDTO(subCategory: CreateSubCategoryDTO, category: CategoryDB): SubCategoryDB {
        return SubCategoryDB(
            id = 0,
            name = subCategory.name,
            descriptor = subCategory.descriptor,
            category = category
        )
    }

    fun categoriesToDTO(category: CategoryDB): CategoryDTO {
        return CategoryDTO(
            id = category.id,
            name = category.name,
            descriptor = category.descriptor,
            userId = category.user.id
        )
    }

    fun categoriesToDTO(categories: List<CategoryDB>): List<CategoryDTO> {
        return buildList {
            for (category in categories) {
                val dto = categoriesToDTO(category)
                add(dto)
            }
        }
    }

    fun subCategoriesToDTO(subCategory: SubCategoryDB): SubCategoryDTO {
        return SubCategoryDTO(
            id = subCategory.id,
            name = subCategory.name,
            descriptor = subCategory.descriptor,
            categoryId = subCategory.category.id
        )
    }

    fun subCategoriesToDTO(subCategories: List<SubCategoryDB>): List<SubCategoryDTO> {
        return buildList {
            for (subCategory in subCategories) {
                val dto = subCategoriesToDTO(subCategory)
                add(dto)
            }
        }
    }
}
