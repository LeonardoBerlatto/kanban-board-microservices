package io.board.kanban.teams.adapter.repository.jpa

import io.board.kanban.teams.domain.entity.Member
import io.board.kanban.teams.domain.entity.MemberId
import io.board.kanban.teams.domain.entity.Team
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaMemberRepository : CrudRepository<Member, MemberId> {
    fun findByTeam(team: Team): List<Member>
}
