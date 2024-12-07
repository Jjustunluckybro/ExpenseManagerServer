package com.app.manager.models.dto


data class CategoryDTO (
    val id: Long,
    val userId: Long,
    val name: String,
    val descriptor: String?
)
