package io.board.kanban.teams.service

import io.board.kanban.teams.domain.Member
import io.board.kanban.teams.domain.MemberId
import io.board.kanban.teams.exception.BadRequestException
import io.board.kanban.teams.exception.NotFoundException
import io.board.kanban.teams.mapper.MemberMapper
import io.board.kanban.teams.repository.MemberRepository
import io.board.kanban.teams.representation.MemberRequest
import java.util.UUID

class MemberService(
    private val repository: MemberRepository,
    private val roleService: RoleService,
    private val teamService: TeamService,
    private val memberMapper: MemberMapper
) {

    fun create(request: MemberRequest): Member {
        val role = roleService.findById(request.roleId)
        val team = teamService.findById(request.teamId)

        val member = memberMapper.toDomain(request, role, team)

        if (repository.existsById(MemberId(member.userId, team))) {
            throw BadRequestException("Member already exists")
        }

        return repository.save(member)
    }

    fun update(request: MemberRequest): Member {
        val team = teamService.findById(request.teamId)
        val existingMember = repository.findById(MemberId(request.userId, team))
            ?: throw NotFoundException("Member does not exist")

        val role = roleService.findById(request.roleId)
        existingMember.role = role

        return repository.save(existingMember)
    }

    fun remove(userId: UUID, teamId: UUID) {
        val id = MemberId(
            userId = userId,
            team = teamService.findById(teamId)
        )

        if (!repository.existsById(id)) {
            throw NotFoundException("Member does not exist")
        }

        repository.deleteById(id)
    }

}
