package io.board.kanban.boards.adapter.rest

import io.board.kanban.boards.adapter.representation.BoardRequest
import io.board.kanban.boards.adapter.representation.BoardResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import java.util.UUID


interface BoardsApi {

    @Operation(summary = "Get a board by id")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Board found"),
            ApiResponse(responseCode = "404", description = "Board not found")
        ]
    )
    fun getById(@Parameter(name = "Board Id", `in` = ParameterIn.PATH) id: UUID): BoardResponse

    @Operation(summary = "Create a new board")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "Board created"),
            ApiResponse(responseCode = "400", description = "Invalid data")
        ]
    )
    fun create(request: BoardRequest): BoardResponse
}