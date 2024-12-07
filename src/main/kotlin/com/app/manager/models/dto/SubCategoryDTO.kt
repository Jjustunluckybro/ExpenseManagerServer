package com.app.manager.models.dto


data class SubCategoryDTO(
    val id: Long,
    val categoryId: Long,
    val name: String,
    val descriptor: String?
)
