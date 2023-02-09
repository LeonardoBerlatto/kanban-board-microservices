package io.board.kanban.teamsapi.service

import io.board.kanban.teamsapi.domain.Member
import io.board.kanban.teamsapi.domain.MemberId
import io.board.kanban.teamsapi.exception.BadRequestException
import io.board.kanban.teamsapi.mapper.MemberMapper
import io.board.kanban.teamsapi.repository.MemberRepository
import io.board.kanban.teamsapi.representation.CreateMemberRequest
import java.util.UUID

class MemberService(
    private val repository: MemberRepository,
    private val roleService: RoleService,
    private val teamService: TeamService,
    private val memberMapper: MemberMapper
) {

    fun create(request: CreateMemberRequest): Member {
        val role = roleService.findById(request.roleId)
        val team = teamService.findById(request.teamId)

        val member = memberMapper.toDomain(request, role, team)

        if (repository.existsById(MemberId(member.userId, team))) {
            throw BadRequestException("Member already exists")
        }

        return repository.save(member)
    }

    fun remove(userId: UUID, teamId: UUID) {
        val id = MemberId(
            userId = userId,
            team = teamService.findById(teamId)
        )
        repository.deleteById(id)
    }

}
