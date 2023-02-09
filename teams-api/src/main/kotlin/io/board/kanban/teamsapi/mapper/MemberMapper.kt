package io.board.kanban.teamsapi.mapper

import io.board.kanban.teamsapi.domain.Member
import io.board.kanban.teamsapi.domain.Role
import io.board.kanban.teamsapi.domain.Team
import io.board.kanban.teamsapi.representation.CreateMemberRequest
import io.board.kanban.teamsapi.representation.MemberResponse
import org.springframework.stereotype.Component

@Component
class MemberMapper {
    fun toDomain(request: CreateMemberRequest, role: Role, team: Team): Member {
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
