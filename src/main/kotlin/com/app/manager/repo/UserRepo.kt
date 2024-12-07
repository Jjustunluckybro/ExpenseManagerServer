package com.app.manager.repo

import com.app.manager.models.database.UserDB
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepo : JpaRepository<UserDB, Long> {

    @Query("""SELECT u FROM UserDB u WHERE u.id = :id""")
    fun findUserById(id: Long): UserDB?
}
