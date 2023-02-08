package io.board.kanban.teamsapi.repository

import io.board.kanban.teamsapi.domain.Role
import io.board.kanban.teamsapi.repository.jpa.JpaRoleRepository
import org.springframework.stereotype.Repository

@Repository
class RoleRepositoryImpl(private val jpaRoleRepository: JpaRoleRepository) : RoleRepository {
    override fun findAll(): List<Role> {
        return jpaRoleRepository.findAll()
    }
}