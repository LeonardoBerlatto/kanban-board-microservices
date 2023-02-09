package io.board.kanban.teamsapi.repository

import io.board.kanban.teamsapi.domain.Role
import java.util.UUID

interface RoleRepository {
    fun findAll(): List<Role>
    fun findById(roleId: UUID): Role?
}
