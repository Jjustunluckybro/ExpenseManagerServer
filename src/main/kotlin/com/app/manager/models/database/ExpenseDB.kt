package com.app.manager.models.database

import com.app.manager.models.enums.Currency
import jakarta.persistence.*
import java.time.Instant

@Table(name = "expense")
@Entity
data class ExpenseDB(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @Column(name = "amount", nullable = false)
    val amount: Float,

    @Column(name = "currency_rate", nullable = false)
    val currencyRate: Float,

    @Column(name = "operation_dt", nullable = false)
    val dt: Instant,

    @Column(name = "currency", nullable = false)
    val currency: Currency,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserDB,

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    val account: AccountDB,

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    val category: CategoryDB,

    @ManyToOne()
    @JoinColumn(name = "sub_category_id", nullable = true)
    val subCategory: SubCategoryDB?,
)
