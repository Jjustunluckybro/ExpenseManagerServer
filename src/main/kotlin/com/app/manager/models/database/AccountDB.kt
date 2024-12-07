package com.app.manager.models.database

import com.app.manager.models.enums.AccountType
import jakarta.persistence.*
import lombok.Getter
import lombok.Setter

@Getter
@Setter
@Entity
@Table(name = "account")
data class AccountDB (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @Column(name = "name")
    val name: String,

    @Column(name = "description")
    val descriptor: String?,

    @Column(name = "balance")
    val balance: Float = 0f,

    @Column(name = "type")
    val type: AccountType,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserDB,
)
