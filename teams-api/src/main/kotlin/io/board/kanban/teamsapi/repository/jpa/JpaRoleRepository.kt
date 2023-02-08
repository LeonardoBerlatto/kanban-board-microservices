package io.board.kanban.teamsapi.repository.jpa

import io.board.kanban.teamsapi.domain.Role
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface JpaRoleRepository : CrudRepository<Role, UUID> {

    override fun findAll(): List<Role>
}
