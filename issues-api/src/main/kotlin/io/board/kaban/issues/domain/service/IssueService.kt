package io.board.kaban.issues.domain.service

import io.board.kaban.issues.adapter.mapper.IssueMapper
import io.board.kaban.issues.adapter.representation.IssueRequest
import io.board.kaban.issues.domain.entity.Issue
import io.board.kaban.issues.domain.repository.IssueRepository
import io.board.kaban.issues.domain.vo.issue.IssuePriority
import io.board.kaban.issues.domain.vo.issue.IssueType
import java.time.LocalDateTime

class IssueService(
    private val repository: IssueRepository,
    private val mapper: IssueMapper
) {

    fun create(request: IssueRequest): Issue {
        request.priority = request.priority ?: IssuePriority.MEDIUM
        request.type = request.type ?: IssueType.TASK
        request.createdDate = request.createdDate ?: LocalDateTime.now()

        val issue = mapper.toDomain(request)
        return repository.save(issue)
    }

}
