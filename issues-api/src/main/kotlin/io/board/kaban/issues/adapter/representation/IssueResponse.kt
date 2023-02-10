package io.board.kaban.issues.adapter.representation

import io.board.kaban.issues.domain.vo.issue.IssuePriority
import io.board.kaban.issues.domain.vo.issue.IssueStatus
import io.board.kaban.issues.domain.vo.issue.IssueType
import java.util.UUID

data class IssueResponse(
    val id: UUID,
    val title: String,
    val description: String,
    val assigneeId: UUID,
    val reporterId: UUID,
    val status: IssueStatus,
    val priority: IssuePriority,
    val type: IssueType,
    val teamId: UUID?
)
