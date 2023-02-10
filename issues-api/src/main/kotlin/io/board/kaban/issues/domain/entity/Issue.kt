package io.board.kaban.issues.domain.entity

import io.board.kaban.issues.domain.vo.issue.IssuePriority
import io.board.kaban.issues.domain.vo.issue.IssueStatus
import io.board.kaban.issues.domain.vo.issue.IssueType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "issues")
data class Issue (
    @Id
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val description: String,
    val assigneeId: UUID,
    val reporterId: UUID,
    @Enumerated(EnumType.STRING)
    val status: IssueStatus,
    @Enumerated(EnumType.STRING)
    val priority: IssuePriority,
    @Enumerated(EnumType.STRING)
    val type: IssueType,
    val teamId: UUID?,
    val createdAt: LocalDateTime
)