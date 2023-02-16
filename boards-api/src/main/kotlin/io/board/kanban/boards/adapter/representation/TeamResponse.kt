package io.board.kanban.boards.adapter.representation

import java.util.UUID

data class TeamResponse(
    val id: UUID,
    val name: String,
    val description: String
)
