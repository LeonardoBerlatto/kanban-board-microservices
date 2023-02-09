package io.board.kanban.teamsapi.repository.jpa

import io.board.kanban.teamsapi.domain.Member
import io.board.kanban.teamsapi.domain.MemberId
import io.board.kanban.teamsapi.domain.Team
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaMemberRepository : CrudRepository<Member, MemberId> {
    fun findByTeam(team: Team): List<Member>
}
