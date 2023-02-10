package io.board.kanban.teams.domain.service

import io.board.kanban.teams.domain.exception.NotFoundException
import io.board.kanban.teams.domain.entity.Role
import io.board.kanban.teams.domain.repository.RoleRepository
import java.util.UUID

class RoleService(private val repository: RoleRepository) {
    fun getAll(): List<Role> {
        return repository.findAll()
    }

    fun findById(roleId: UUID): Role {
        return repository.findById(roleId) ?: throw NotFoundException("Role not found")
    }

}
