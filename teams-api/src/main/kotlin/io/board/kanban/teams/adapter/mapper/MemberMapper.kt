package io.board.kanban.teams.adapter.mapper

import io.board.kanban.teams.domain.entity.Member
import io.board.kanban.teams.domain.entity.Role
import io.board.kanban.teams.domain.entity.Team
import io.board.kanban.teams.adapter.representation.MemberRequest
import io.board.kanban.teams.adapter.representation.MemberResponse
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
