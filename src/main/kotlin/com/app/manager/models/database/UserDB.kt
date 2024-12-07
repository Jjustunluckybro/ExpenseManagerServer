package com.app.manager.models.database

import jakarta.persistence.*
import lombok.Getter
import lombok.Setter

@Entity
@Getter
@Setter
@Table(name = "users")
data class UserDB(
    @Id
    val id: Long,

    @Column(name = "name", nullable = true)
    val name: String?,

    @Column(name = "username", nullable = false)
    val username: String,

    @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true, optional = true)
    val settings: UserSettingsDB? = null,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val accountDBS: List<AccountDB> = emptyList()
)
