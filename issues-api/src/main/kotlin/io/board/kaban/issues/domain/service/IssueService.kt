package io.board.kaban.issues.domain.service

import io.board.kaban.issues.adapter.mapper.IssueMapper
import io.board.kaban.issues.adapter.representation.IssueRequest
import io.board.kaban.issues.domain.entity.Issue
import io.board.kaban.issues.domain.exception.NotFoundException
import io.board.kaban.issues.domain.repository.IssueRepository
import io.board.kaban.issues.domain.vo.issue.IssuePriority
import io.board.kaban.issues.domain.vo.issue.IssueStatus
import io.board.kaban.issues.domain.vo.issue.IssueType
import java.time.LocalDateTime
import java.util.UUID

class IssueService(
    private val repository: IssueRepository,
    private val mapper: IssueMapper
) {

    fun create(request: IssueRequest): Issue {
        request.priority = request.priority ?: IssuePriority.MEDIUM
        request.type = request.type ?: IssueType.TASK
        request.createdDate = request.createdDate ?: LocalDateTime.now()
        request.status = request.status ?: IssueStatus.OPEN

        val issue = mapper.toDomain(request)
        return repository.save(issue)
    }

    fun markAsDeleted(id: UUID) {
        val issue = repository.findById(id).orElseThrow { NotFoundException("Issue not found") }
        issue.active = false
        repository.save(issue)
    }

}
