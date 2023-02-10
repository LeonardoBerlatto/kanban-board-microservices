package io.board.kanban.teams.adapter.mapper

import io.board.kanban.teams.domain.entity.Team
import io.board.kanban.teams.adapter.representation.TeamResponse
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class TeamMapper(private val memberMapper: MemberMapper) {

    fun toResponse(team: Team): TeamResponse {
        return TeamResponse(
            id = team.id,
            name = team.name,
            members = team.members.map { memberMapper.toResponse(it) }
        )
    }

    fun toResponse(team: Page<Team>): Page<TeamResponse> {
        return team.map { toResponse(it) }
    }
}
