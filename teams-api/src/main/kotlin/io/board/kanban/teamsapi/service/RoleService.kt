package io.board.kanban.teamsapi.service

import io.board.kanban.teamsapi.exception.NotFoundException
import io.board.kanban.teamsapi.domain.Role
import io.board.kanban.teamsapi.repository.RoleRepository
import java.util.UUID

class RoleService(private val repository: RoleRepository) {
    fun getAll(): List<Role> {
        return repository.findAll()
    }

    fun findById(roleId: UUID): Role {
        return repository.findById(roleId) ?: throw NotFoundException("Role not found")
    }

}
