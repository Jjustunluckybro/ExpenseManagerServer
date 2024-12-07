package com.app.manager.models.database

import jakarta.persistence.*

@Entity
@Table
data class CategoryDB(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserDB,

    @Column(name = "name")
    val name: String,

    @Column(name = "descriptor", nullable = true)
    val descriptor: String?
)
