package io.board.kaban.issues.domain.service

import io.board.kaban.issues.adapter.mapper.IssueMapper
import io.board.kaban.issues.adapter.representation.IssueRequest
import io.board.kaban.issues.domain.entity.Issue
import io.board.kaban.issues.domain.repository.IssueRepository
import io.board.kaban.issues.domain.vo.issue.IssuePriority
import io.board.kaban.issues.domain.vo.issue.IssueType
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import java.time.LocalDateTime
import java.util.UUID

class IssueServiceTest {

    private val repository = mockk<IssueRepository>()
    private val mapper = mockk<IssueMapper>()
    private val service = IssueService(repository, mapper)

    private val issueSlot = slot<IssueRequest>()

    @Test
    fun `should create issue`() {
        // Arrange
        val request = IssueRequest(
            title = "Test",
            description = "Test description",
            assigneeId = UUID.randomUUID(),
            reporterId = UUID.randomUUID(),
            createdDate = LocalDateTime.now(),
            priority = IssuePriority.HIGH,
            type = IssueType.BUG,
            teamId = UUID.randomUUID()
        )

        val issue = Issue(
            title = request.title,
            description = request.description,
            assigneeId = request.assigneeId,
            reporterId = request.reporterId,
            createdAt = request.createdDate!!,
            priority = request.priority!!,
            type = request.type!!,
            teamId = request.teamId!!
        )

        every { mapper.toDomain(request) } returns issue
        every { repository.save(issue) } returns issue

        // Act
        val result = service.create(request)

        // Assert
        assertThat(result.title).isEqualTo(request.title)
        assertThat(result.description).isEqualTo(request.description)
        assertThat(result.assigneeId).isEqualTo(request.assigneeId)
        assertThat(result.reporterId).isEqualTo(request.reporterId)
        assertThat(result.createdAt).isEqualTo(request.createdDate)
        assertThat(result.priority).isEqualTo(request.priority)
        assertThat(result.type).isEqualTo(request.type)
        assertThat(result.teamId).isEqualTo(request.teamId)
    }

    @Test
    fun `should create issue with default values`() {
        // Arrange
        val request = IssueRequest(
            title = "Test",
            description = "Test description",
            assigneeId = UUID.randomUUID(),
            reporterId = UUID.randomUUID(),
            teamId = UUID.randomUUID(),
            createdDate = null,
            priority = null,
            type = null
        )

        val createdIssue = Issue(
            title = request.title,
            description = request.description,
            assigneeId = request.assigneeId,
            reporterId = request.reporterId,
            createdAt = LocalDateTime.now(),
            priority = IssuePriority.MEDIUM,
            type = IssueType.TASK,
            teamId = request.teamId!!
        )

        every { mapper.toDomain(capture(issueSlot)) } returns createdIssue
        every { repository.save(createdIssue) } returns createdIssue

        // Act
        val result = service.create(request)

        // Assert
        assertThat(result.title).isEqualTo(request.title)
        assertThat(result.description).isEqualTo(request.description)
        assertThat(result.assigneeId).isEqualTo(request.assigneeId)
        assertThat(result.reporterId).isEqualTo(request.reporterId)
        assertThat(result.createdAt).isEqualTo(createdIssue.createdAt)
        assertThat(result.priority).isEqualTo(createdIssue.priority)
        assertThat(result.type).isEqualTo(createdIssue.type)
        assertThat(result.teamId).isEqualTo(request.teamId)
    }
}