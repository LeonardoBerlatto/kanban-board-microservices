package io.board.kaban.issues.domain.service

import io.board.kaban.issues.adapter.mapper.IssueMapper
import io.board.kaban.issues.adapter.representation.IssueRequest
import io.board.kaban.issues.domain.entity.Issue
import io.board.kaban.issues.domain.exception.NotFoundException
import io.board.kaban.issues.domain.repository.IssueRepository
import io.board.kaban.issues.domain.vo.issue.IssuePriority
import io.board.kaban.issues.domain.vo.issue.IssueStatus
import io.board.kaban.issues.domain.vo.issue.IssueType
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

import java.time.LocalDateTime
import java.util.Optional
import java.util.UUID

class IssueServiceTest {

    private val repository = mockk<IssueRepository>()
    private val mapper = mockk<IssueMapper>()
    private val service = IssueService(repository, mapper)

    private val issueRequestSlot = slot<IssueRequest>()
    private val issueSlot = slot<Issue>()

    @Nested
    @DisplayName("Create issue")
    inner class CreateIssue {
        @Test
        fun `should create issue`() {
            // Arrange
            val request = IssueRequest(
                title = "Test",
                description = "Test description",
                reporterId = UUID.randomUUID(),
                assigneeId = UUID.randomUUID(),
                priority = IssuePriority.HIGH,
                type = IssueType.BUG,
                teamId = UUID.randomUUID(),
                createdDate = LocalDateTime.now(),
                status = null
            )

            val issue = Issue(
                title = request.title,
                description = request.description,
                assigneeId = request.assigneeId,
                reporterId = request.reporterId,
                createdAt = request.createdDate!!,
                priority = request.priority!!,
                type = request.type!!,
                teamId = request.teamId!!,
                status = IssueStatus.OPEN
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
            assertThat(result.status).isEqualTo(IssueStatus.OPEN)
        }

        @Test
        fun `should create issue with default values`() {
            // Arrange
            val request = IssueRequest(
                title = "Test",
                description = "Test description",
                reporterId = UUID.randomUUID(),
                assigneeId = UUID.randomUUID(),
                priority = null,
                type = null,
                teamId = UUID.randomUUID(),
                createdDate = null,
                status = null
            )

            val createdIssue = Issue(
                title = request.title,
                description = request.description,
                assigneeId = request.assigneeId,
                reporterId = request.reporterId,
                createdAt = LocalDateTime.now(),
                priority = IssuePriority.MEDIUM,
                type = IssueType.TASK,
                teamId = request.teamId!!,
                status = IssueStatus.OPEN
            )

            every { mapper.toDomain(capture(issueRequestSlot)) } returns createdIssue
            every { repository.save(createdIssue) } returns createdIssue

            // Act
            service.create(request)
            val result = issueRequestSlot.captured

            // Assert
            assertThat(result.title).isEqualTo(request.title)
            assertThat(result.description).isEqualTo(request.description)
            assertThat(result.assigneeId).isEqualTo(request.assigneeId)
            assertThat(result.reporterId).isEqualTo(request.reporterId)
            assertThat(result.createdDate).isEqualTo(request.createdDate)
            assertThat(result.teamId).isEqualTo(request.teamId)
            assertThat(result.priority).isEqualTo(IssuePriority.MEDIUM)
            assertThat(result.type).isEqualTo(IssueType.TASK)
            assertThat(issueRequestSlot.captured.status).isEqualTo(IssueStatus.OPEN)
        }
    }

    @Nested
    @DisplayName("Update issue")
    inner class UpdateIssue {
        @Test
        fun `should update issue when issue exists`() {
            // Arrange
            val issueId = UUID.randomUUID()

            val issue = aIssue(issueId)

            every { repository.findById(issueId) } returns Optional.of(issue)
            every { repository.save(capture(issueSlot)) } returns issue

            val request = aIssueRequest()

            // Act
            service.update(issueId, request)
            val result = issueSlot.captured

            // Assert
            assertThat(result.id).isEqualTo(issueId)
            assertThat(result.title).isEqualTo(request.title)
            assertThat(result.description).isEqualTo(request.description)
            assertThat(result.assigneeId).isEqualTo(request.assigneeId)
            assertThat(result.reporterId).isEqualTo(request.reporterId)
            assertThat(result.createdAt).isEqualTo(issue.createdAt)
            assertThat(result.priority).isEqualTo(request.priority)
            assertThat(result.type).isEqualTo(request.type)
            assertThat(result.teamId).isEqualTo(request.teamId)
            assertThat(result.status).isEqualTo(request.status)
        }

        @Test
        fun `should throw an exception when issue does not exist`() {
            // Arrange
            val issueId = UUID.randomUUID()

            every { repository.findById(issueId) } returns Optional.empty()

            // Act
            val exception = assertThrows<NotFoundException> {
                service.update(issueId, aIssueRequest())
            }

            // Assert
            assertThat(exception.message).isEqualTo("Issue not found")
        }
    }

    @Nested
    @DisplayName("Inactive issue")
    inner class InactiveIssue {
        @Test
        fun `should mark issue as inactive when issue exists`() {
            // Arrange
            val issueId = UUID.randomUUID()

            every { repository.findById(issueId) } returns Optional.of(aIssue(issueId))
            every { repository.save(capture(issueSlot)) } returns aIssue(issueId)

            // Act
            service.inactivate(issueId)
            val result = issueSlot.captured

            // Assert
            assertThat(result.active).isFalse
        }

        @Test
        fun `should throw an exception when issue does not exist`() {
            // Arrange
            val issueId = UUID.randomUUID()

            every { repository.findById(issueId) } returns Optional.empty()

            // Act
            val exception = assertThrows<NotFoundException> {
                service.inactivate(issueId)
            }

            // Assert
            assertThat(exception.message).isEqualTo("Issue not found")
        }
    }

    private fun aIssue(issueId: UUID) = Issue(
        id = issueId,
        title = "Test",
        description = "Testing a issue",
        assigneeId = UUID.randomUUID(),
        reporterId = UUID.randomUUID(),
        createdAt = LocalDateTime.now(),
        priority = IssuePriority.MEDIUM,
        type = IssueType.TASK,
        teamId = UUID.randomUUID(),
        status = IssueStatus.IN_PROGRESS
    )

    private fun aIssueRequest() = IssueRequest(
        title = "Test",
        description = "Test description",
        reporterId = UUID.randomUUID(),
        assigneeId = UUID.randomUUID(),
        priority = IssuePriority.HIGH,
        type = IssueType.BUG,
        teamId = UUID.randomUUID(),
        createdDate = LocalDateTime.now(),
        status = IssueStatus.DONE
    )

}