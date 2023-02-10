package io.board.kaban.issues.adapter.representation

import io.board.kaban.issues.domain.vo.issue.IssuePriority
import io.board.kaban.issues.domain.vo.issue.IssueType
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime
import java.util.UUID

data class IssueRequest(
    @NotBlank(message = "Title is required")
    val title: String,
    @NotBlank(message = "Description is required")
    val description: String,
    @NotNull(message = "Assignee is required")
    val assigneeId: UUID,
    @NotNull(message = "Reporter is required")
    val reporterId: UUID,
    var priority: IssuePriority?,
    var type: IssueType?,
    var teamId: UUID?,
    var createdDate: LocalDateTime?
)