package com.app.manager.models.database

import jakarta.persistence.*

@Table()
@Entity
data class SubCategoryDB(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @ManyToOne
    @JoinColumn(name = "category_id")
    val category: CategoryDB,

    @Column(name = "name")
    val name: String,

    @Column(name = "descriptor", nullable = true)
    val descriptor: String?
)
