package io.board.kaban.issues.adapter.representation

import com.fasterxml.jackson.annotation.JsonFormat
import io.board.kaban.issues.domain.vo.issue.IssuePriority
import io.board.kaban.issues.domain.vo.issue.IssueStatus
import io.board.kaban.issues.domain.vo.issue.IssueType
import java.time.LocalDateTime
import java.util.UUID

data class IssueResponse(
    val id: UUID,
    val title: String,
    val description: String,
    val reporterId: UUID,
    val status: IssueStatus,
    val priority: IssuePriority,
    val type: IssueType,
    val assigneeId: UUID?,
    val teamId: UUID?,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdAt: LocalDateTime
)
