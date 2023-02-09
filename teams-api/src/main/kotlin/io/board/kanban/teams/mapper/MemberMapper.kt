package io.board.kanban.teams.mapper

import io.board.kanban.teams.domain.Member
import io.board.kanban.teams.domain.Role
import io.board.kanban.teams.domain.Team
import io.board.kanban.teams.representation.MemberRequest
import io.board.kanban.teams.representation.MemberResponse
import org.springframework.stereotype.Component

@Component
class MemberMapper {
    fun toDomain(request: MemberRequest, role: Role, team: Team): Member {
        return Member(
            userId = request.userId,
            team = team,
            role = role
        )
    }

    fun toResponse(member: Member): MemberResponse {
        return MemberResponse(
            userId = member.userId,
            teamId = member.team.id,
            roleId = member.role.id
        )
    }

}
