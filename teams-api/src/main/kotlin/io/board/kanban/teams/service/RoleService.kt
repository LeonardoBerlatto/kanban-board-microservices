package io.board.kanban.teams.service

import io.board.kanban.teams.exception.NotFoundException
import io.board.kanban.teams.domain.Role
import io.board.kanban.teams.repository.RoleRepository
import java.util.UUID

class RoleService(private val repository: RoleRepository) {
    fun getAll(): List<Role> {
        return repository.findAll()
    }

    fun findById(roleId: UUID): Role {
        return repository.findById(roleId) ?: throw NotFoundException("Role not found")
    }

}
