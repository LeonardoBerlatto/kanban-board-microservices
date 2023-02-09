package io.board.kanban.teamsapi.service

import io.board.kanban.teamsapi.exception.NotFoundException
import io.board.kanban.teamsapi.domain.Team
import io.board.kanban.teamsapi.exception.BadRequestException
import io.board.kanban.teamsapi.repository.TeamRepository
import io.board.kanban.teamsapi.representation.CreateTeamRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID

class TeamService(
    private val repository: TeamRepository
) {

    fun findById(id: UUID): Team {
        return repository.findById(id) ?: throw NotFoundException("Team not found")
    }

    fun create(request: CreateTeamRequest): Team {
        if (request.name.isBlank()) {
            throw BadRequestException("Team name cannot be blank")
        }

        return repository.save(Team(name = request.name, members = emptyList()))
    }

    fun findTeamByName(name: String, pageable: Pageable): Page<Team> {
        return repository.findByName(name, pageable)
    }

}
