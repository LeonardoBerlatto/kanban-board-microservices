package io.board.kanban.boards.adapter.repository

import io.board.kanban.boards.adapter.repository.feign.TeamFeignClient
import io.board.kanban.boards.domain.repository.TeamRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class TeamRepositoryImpl(val client: TeamFeignClient) : TeamRepository {

    override fun existsById(id: UUID): Boolean {
        return client.findById(id) != null
    }
}