package io.board.kanban.teamsapi.repository

import io.board.kanban.teamsapi.domain.Member
import io.board.kanban.teamsapi.domain.MemberId
import io.board.kanban.teamsapi.domain.Team

interface MemberRepository {
    fun existsById(id: MemberId): Boolean
    fun save(member: Member): Member
    fun findByTeam(team: Team): List<Member>
}
