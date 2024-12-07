package com.app.manager.models.database

import com.app.manager.models.enums.Currency
import jakarta.persistence.*
import lombok.Getter
import lombok.Setter


@Getter
@Setter
@Entity
@Table(name = "users_settings")
data class UserSettingsDB(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @OneToOne
    @JoinColumn(name = "user_id")
    val user: UserDB,

    @OneToOne
    @JoinColumn(name = "main_account_id", nullable = true)
    val mainAccountDB: AccountDB?,

    @Column(name = "currency")
    val currency: Currency,

    @Column(name = "timezone")
    val timeZone: Int
)
