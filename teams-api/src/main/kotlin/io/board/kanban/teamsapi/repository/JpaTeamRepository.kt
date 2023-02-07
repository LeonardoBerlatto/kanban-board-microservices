package io.board.kanban.teamsapi.repository

import io.board.kanban.teamsapi.domain.Team
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaTeamRepository : CrudRepository<Team, String> {
    fun findByNameContains(name: String, pageable: Pageable): Page<Team>
    fun save(team: Team): Team
}
