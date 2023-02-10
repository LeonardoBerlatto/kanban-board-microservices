package io.board.kanban.teams.adapter.repository.jpa

import io.board.kanban.teams.domain.entity.Role
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface JpaRoleRepository : CrudRepository<Role, UUID> {

    override fun findAll(): List<Role>
}
