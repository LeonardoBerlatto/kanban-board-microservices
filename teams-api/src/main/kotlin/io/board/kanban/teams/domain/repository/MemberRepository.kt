package io.board.kanban.teams.domain.repository

import io.board.kanban.teams.domain.entity.Member
import io.board.kanban.teams.domain.entity.MemberId
import io.board.kanban.teams.domain.entity.Team

interface MemberRepository {
    fun existsById(id: MemberId): Boolean
    fun save(member: Member): Member
    fun findByTeam(team: Team): List<Member>
    fun deleteById(id: MemberId)
    fun findById(memberId: MemberId): Member?
}
