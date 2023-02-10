package io.board.kanban.teams.adapter.controller

import io.board.kanban.teams.adapter.mapper.RoleMapper
import io.board.kanban.teams.adapter.representation.RoleResponse
import io.board.kanban.teams.domain.service.RoleService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/roles")
class RoleController(val service: RoleService, val mapper: RoleMapper) {

    @GetMapping
    fun getAll(): List<RoleResponse> {
        return mapper.toResponse(service.getAll())
    }
}