package io.board.kanban.teams.repository.jpa

import io.board.kanban.teams.domain.Team
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface JpaTeamRepository : CrudRepository<Team, UUID> {
    fun findByNameContains(name: String, pageable: Pageable): Page<Team>
    fun save(team: Team): Team
}
