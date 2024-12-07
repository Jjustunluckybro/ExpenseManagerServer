package com.app.manager.models.dto

import lombok.Builder
import lombok.Getter
import lombok.Setter

@Getter
@Setter
@Builder
data class UserDTO(
    val id: Long?,
    val name: String?,
    val username: String,
    val userSettingsId: Long?,
)
