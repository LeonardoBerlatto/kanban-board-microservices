package io.board.kanban.teams.repository.jpa

import io.board.kanban.teams.domain.Member
import io.board.kanban.teams.domain.MemberId
import io.board.kanban.teams.domain.Team
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaMemberRepository : CrudRepository<Member, MemberId> {
    fun findByTeam(team: Team): List<Member>
}
