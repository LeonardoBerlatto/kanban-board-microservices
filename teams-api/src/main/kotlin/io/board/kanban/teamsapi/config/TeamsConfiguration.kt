package io.board.kanban.teamsapi.config

import io.board.kanban.teamsapi.repository.TeamRepository
import io.board.kanban.teamsapi.service.TeamService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TeamsConfiguration {

    @Bean
    fun teamService(repository: TeamRepository): TeamService {
        return TeamService(repository)
    }
}