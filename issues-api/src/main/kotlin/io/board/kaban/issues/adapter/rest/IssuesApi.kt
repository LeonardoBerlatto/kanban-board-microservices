package io.board.kaban.issues.adapter.rest

import io.board.kaban.issues.adapter.representation.IssueRequest
import io.board.kaban.issues.adapter.representation.IssueResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import java.util.UUID


interface IssuesApi {

    @Operation(summary = "Get a issue by id")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Issue found"),
            ApiResponse(responseCode = "404", description = "Issue not found")
        ]
    )
    fun getById(id: UUID): ResponseEntity<IssueResponse>

    @Operation(summary = "Create a new issue")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "Issue created"),
            ApiResponse(responseCode = "400", description = "Invalid data")
        ]
    )
    fun create(request: IssueRequest): ResponseEntity<IssueResponse>

    @Operation(summary = "Update a issue")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Issue updated"),
            ApiResponse(responseCode = "404", description = "Issue not found")
        ]
    )
    fun update(@Parameter(name = "Issue Id", `in` = ParameterIn.PATH) id: UUID, request: IssueRequest): ResponseEntity<IssueResponse>

    @Operation(summary = "Delete a issue")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "Issue deleted"),
            ApiResponse(responseCode = "404", description = "Issue not found")
        ]
    )
    fun delete(@Parameter(name = "Issue Id", `in` = ParameterIn.PATH) id: UUID): ResponseEntity<Void>
}