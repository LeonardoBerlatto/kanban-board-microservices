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
    var title: String,
    var description: String,
    var assigneeId: UUID?,
    var reporterId: UUID,
    @Enumerated(EnumType.STRING)
    var status: IssueStatus = IssueStatus.OPEN,
    @Enumerated(EnumType.STRING)
    var priority: IssuePriority,
    @Enumerated(EnumType.STRING)
    var type: IssueType,
    var teamId: UUID?,
    val createdAt: LocalDateTime,
    var active: Boolean = true
)