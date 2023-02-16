package io.board.kanban.boards.adapter.representation

import java.util.UUID

data class BoardResponse(
    val id: UUID,
    val name: String,
    val description: String,
    val teamId: UUID,
    val issuesIds: List<UUID>
)
