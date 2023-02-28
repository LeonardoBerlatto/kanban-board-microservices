package io.board.kanban.teams.adapter.rest

import io.board.kanban.teams.adapter.representation.CreateTeamRequest
import io.board.kanban.teams.adapter.representation.TeamResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import java.util.UUID

interface TeamsApi {

    @Operation(summary = "Get a team by id")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Team found"),
            ApiResponse(responseCode = "404", description = "Team not found")
        ]
    )
    fun getById(@Parameter(name = "Team Id", `in` = ParameterIn.PATH) id: UUID): TeamResponse

    @Operation(summary = "Search teams by name")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Teams response"),
        ]
    )
    fun getByName(@Parameter(name = "Team name", `in` = ParameterIn.QUERY) name: String): Page<TeamResponse>

    @Operation(summary = "Create a team")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "Team created"),
            ApiResponse(responseCode = "400", description = "Invalid data")
        ]
    )
    fun create(request: CreateTeamRequest): ResponseEntity<TeamResponse>
}
