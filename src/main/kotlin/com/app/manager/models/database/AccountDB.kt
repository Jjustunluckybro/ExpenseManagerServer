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
    var name: String,

    @Column(name = "description")
    var descriptor: String?,

    @Column(name = "balance")
    var balance: Float = 0f,

    @Column(name = "type")
    var type: AccountType,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserDB,
)
