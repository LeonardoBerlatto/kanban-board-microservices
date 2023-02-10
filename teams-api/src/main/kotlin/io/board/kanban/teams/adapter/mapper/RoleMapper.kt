package io.board.kanban.teams.adapter.mapper

import io.board.kanban.teams.domain.entity.Role
import io.board.kanban.teams.adapter.representation.RoleResponse
import org.springframework.stereotype.Component

@Component
class RoleMapper {

    fun toResponse(role: Role): RoleResponse {
        return RoleResponse(
            id = role.id,
            name = role.name
        )
    }

    fun toResponse(roles: List<Role>): List<RoleResponse> {
        return roles.map { toResponse(it) }
    }

}
