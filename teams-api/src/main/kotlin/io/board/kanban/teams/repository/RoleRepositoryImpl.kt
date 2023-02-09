package io.board.kanban.teams.repository

import io.board.kanban.teams.domain.Role
import io.board.kanban.teams.repository.jpa.JpaRoleRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class RoleRepositoryImpl(private val jpaRoleRepository: JpaRoleRepository) : RoleRepository {
    override fun findAll(): List<Role> {
        return jpaRoleRepository.findAll()
    }

    override fun findById(roleId: UUID): Role? {
        return jpaRoleRepository.findById(roleId).orElse(null)
    }
}