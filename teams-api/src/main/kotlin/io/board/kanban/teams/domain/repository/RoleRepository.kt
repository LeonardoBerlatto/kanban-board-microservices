package io.board.kanban.teams.domain.repository

import io.board.kanban.teams.domain.entity.Role
import java.util.UUID

interface RoleRepository {
    fun findAll(): List<Role>
    fun findById(roleId: UUID): Role?
}
