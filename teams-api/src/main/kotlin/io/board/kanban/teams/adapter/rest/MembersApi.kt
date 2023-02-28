package io.board.kanban.teams.adapter.rest

import io.board.kanban.teams.adapter.representation.MemberRequest
import io.board.kanban.teams.adapter.representation.MemberResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import java.util.UUID

interface MembersApi {

    @Operation(summary = "Create a new membership relation")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "Membership created"),
            ApiResponse(responseCode = "400", description = "Invalid data")
        ]
    )
    fun create(request: MemberRequest): ResponseEntity<MemberResponse>

    @Operation(summary = "Update a membership relation")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Membership updated"),
            ApiResponse(responseCode = "404", description = "Membership not found")
        ]
    )
    fun update(request: MemberRequest): ResponseEntity<MemberResponse>

    @Operation(summary = "Delete a membership relation")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "Membership deleted"),
            ApiResponse(responseCode = "404", description = "Membership not found")
        ]
    )
    fun remove(@Parameter(name = "User Id", `in` = ParameterIn.PATH) userId: UUID,
               @Parameter(name = "Team Id", `in` = ParameterIn.PATH) teamId: UUID): ResponseEntity<Void>
}