package io.board.kanban.boards.domain.service

import io.board.kanban.boards.domain.repository.TeamRepository
import java.util.UUID

class TeamService(private val teamRepository: TeamRepository) {

    fun existById(id: UUID): Boolean {
        return teamRepository.existsById(id)
    }

}
