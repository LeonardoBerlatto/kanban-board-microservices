package io.board.kanban.teams.adapter.rest.impl

import io.board.kanban.teams.adapter.mapper.RoleMapper
import io.board.kanban.teams.adapter.representation.RoleResponse
import io.board.kanban.teams.adapter.rest.RolesApi
import io.board.kanban.teams.domain.service.RoleService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/roles")
class RolesController(
    val service: RoleService,
    val mapper: RoleMapper
): RolesApi {

    @GetMapping
    override fun getAll(): List<RoleResponse> {
        return mapper.toResponse(service.getAll())
    }
}