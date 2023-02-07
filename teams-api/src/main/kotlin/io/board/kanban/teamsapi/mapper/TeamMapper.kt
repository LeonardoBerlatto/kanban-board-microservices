package io.board.kanban.teamsapi.mapper

import io.board.kanban.teamsapi.domain.Team
import io.board.kanban.teamsapi.representation.TeamResponse
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class TeamMapper() {

    fun toResponse(team: Team): TeamResponse {
        return TeamResponse(
            id = team.id,
            name = team.name
        )
    }

    fun toResponse(team: Page<Team>): Page<TeamResponse> {
        return team.map { toResponse(it) }
    }
}
