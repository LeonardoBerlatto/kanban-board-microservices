package io.board.kanban.teamsapi.service

import io.board.kanban.teamsapi.domain.Role
import io.board.kanban.teamsapi.repository.RoleRepository

class RoleService(private val repository: RoleRepository) {
    fun getAll(): List<Role> {
        return repository.findAll()
    }

}
