package io.board.kanban.boards.adapter.representation

import java.util.UUID

data class IssueResponse (
    val id: UUID,
    val title: String,
    val description: String,
    val status: String
)

