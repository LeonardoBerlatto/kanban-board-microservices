package io.board.kanban.teams.repository

import io.board.kanban.teams.domain.Role
import java.util.UUID

interface RoleRepository {
    fun findAll(): List<Role>
    fun findById(roleId: UUID): Role?
}
