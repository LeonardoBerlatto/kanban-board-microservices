package io.board.kanban.teams.adapter.rest

import io.board.kanban.teams.adapter.representation.RoleResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses


interface RolesApi {

    @Operation(summary = "Get all roles")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Roles found"),
        ]
    )
    fun getAll(): List<RoleResponse>
}
