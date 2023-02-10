package io.board.kaban.issues.adapter.mapper

import io.board.kaban.issues.adapter.representation.IssueRequest
import io.board.kaban.issues.adapter.representation.IssueResponse
import io.board.kaban.issues.domain.entity.Issue
import org.springframework.stereotype.Component

@Component
class IssueMapper {
    fun toDomain(request: IssueRequest): Issue {
        return Issue(
            title = request.title,
            description = request.description,
            assigneeId = request.assigneeId,
            reporterId = request.reporterId,
            status = request.status!!,
            type = request.type!!,
            priority = request.priority!!,
            teamId = request.teamId,
            createdAt = request.createdDate!!
        )
    }

    fun toResponse(domain: Issue): IssueResponse {
        return IssueResponse(
            id = domain.id,
            title = domain.title,
            description = domain.description,
            assigneeId = domain.assigneeId,
            reporterId = domain.reporterId,
            status = domain.status,
            priority = domain.priority,
            type = domain.type,
            teamId = domain.teamId
        )
    }

}
