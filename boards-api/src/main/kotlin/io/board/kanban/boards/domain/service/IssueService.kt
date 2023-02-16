package io.board.kanban.boards.domain.service

import io.board.kanban.boards.domain.repository.IssueRepository
import java.util.UUID

class IssueService(private val issueRepository: IssueRepository) {
    fun existById(id: UUID): Boolean {
        return issueRepository.existById(id)
    }

}
