package io.board.kanban.teamsapi.service

import io.board.kanban.teamsapi.domain.Team
import io.board.kanban.teamsapi.repository.TeamRepository
import io.board.kanban.teamsapi.representation.CreateTeamRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

class TeamService(
    private val repository: TeamRepository
) {

    fun create(request: CreateTeamRequest): Team {
        if (request.name.isBlank()) {
            throw IllegalArgumentException("Team name cannot be blank")
        }

        return repository.save(Team(name = request.name))
    }

    fun findTeamByName(name: String, pageable: Pageable): Page<Team> {
        return repository.findByName(name, pageable)
    }

}
