package io.board.kanban.teams.controller

import io.board.kanban.teams.mapper.RoleMapper
import io.board.kanban.teams.representation.RoleResponse
import io.board.kanban.teams.service.RoleService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/roles")
class RoleController(val service: RoleService, val mapper: RoleMapper) {

    @GetMapping
    fun getAll(): List<RoleResponse> {
        return mapper.toResponse(service.getAll())
    }
}