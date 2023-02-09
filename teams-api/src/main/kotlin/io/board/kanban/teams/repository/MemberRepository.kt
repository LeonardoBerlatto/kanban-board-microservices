package io.board.kanban.teams.repository

import io.board.kanban.teams.domain.Member
import io.board.kanban.teams.domain.MemberId
import io.board.kanban.teams.domain.Team

interface MemberRepository {
    fun existsById(id: MemberId): Boolean
    fun save(member: Member): Member
    fun findByTeam(team: Team): List<Member>
    fun deleteById(id: MemberId)
    fun findById(memberId: MemberId): Member?
}
