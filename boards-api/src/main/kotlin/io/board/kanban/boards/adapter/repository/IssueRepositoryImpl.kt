package io.board.kanban.boards.adapter.repository

import io.board.kanban.boards.adapter.repository.feign.IssueFeignClient
import io.board.kanban.boards.domain.repository.IssueRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class IssueRepositoryImpl(private val client: IssueFeignClient) : IssueRepository {

    override fun existById(id: UUID): Boolean {
        return client.findById(id) != null
    }
}