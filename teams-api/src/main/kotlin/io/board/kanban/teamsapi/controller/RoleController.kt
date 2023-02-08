package io.board.kanban.teamsapi.controller

import io.board.kanban.teamsapi.mapper.RoleMapper
import io.board.kanban.teamsapi.representation.RoleResponse
import io.board.kanban.teamsapi.service.RoleService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/roles")
class RoleController(val service: RoleService, val mapper: RoleMapper) {

    @GetMapping
    fun getAll(): List<RoleResponse> {
        return mapper.toResponse(service.getAll())
    }
}