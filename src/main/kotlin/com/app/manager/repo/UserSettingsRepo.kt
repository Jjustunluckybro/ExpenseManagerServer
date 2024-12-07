package com.app.manager.repo

import com.app.manager.models.database.UserSettingsDB
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserSettingsRepo : JpaRepository<UserSettingsDB, Long> {

    @Query("""SELECT u FROM UserSettingsDB u WHERE u.user.id = :id""")
    fun findUserSettingsByUserId(id: Long): UserSettingsDB?
}
