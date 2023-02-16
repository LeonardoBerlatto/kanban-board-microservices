package io.board.kanban.boards.adapter.mapper

import io.board.kanban.boards.adapter.representation.BoardRequest
import io.board.kanban.boards.adapter.representation.BoardResponse
import io.board.kanban.boards.domain.entity.Board
import org.springframework.stereotype.Component

@Component
class BoardMapper {
    fun toDomain(request: BoardRequest) =
        Board(
            name = request.name,
            description = request.description,
            teamId = request.teamId,
            issues = request.issuesIds
        )

    fun toResponse(board: Board): BoardResponse =
        BoardResponse(
            id = board.id,
            name = board.name,
            description = board.description,
            teamId = board.teamId,
            issuesIds = board.issues
        )

}
