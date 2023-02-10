package io.board.kaban.issues.adapter.representation

import java.util.UUID

data class IssueResponse(
    val id: UUID,
    val title: String,
    val description: String,
    val assigneeId: UUID,
    val reporterId: UUID,
    val priority: String,
    val type: String,
    val teamId: UUID?
)
