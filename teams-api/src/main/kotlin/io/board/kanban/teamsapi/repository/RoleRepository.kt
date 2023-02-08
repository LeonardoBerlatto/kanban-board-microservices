package io.board.kanban.teamsapi.repository

import io.board.kanban.teamsapi.domain.Role

interface RoleRepository {
    fun findAll(): List<Role>
}
