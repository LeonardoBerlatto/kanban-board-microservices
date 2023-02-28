package io.board.kanban.boards.application.config

import io.board.kanban.boards.domain.repository.BoardRepository
import io.board.kanban.boards.domain.repository.IssueRepository
import io.board.kanban.boards.domain.repository.TeamRepository
import io.board.kanban.boards.domain.service.BoardService
import io.board.kanban.boards.domain.service.IssueService
import io.board.kanban.boards.domain.service.TeamService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BoardsConfiguration {

    @Bean
    fun teamService(repository: TeamRepository) = TeamService(repository)


    @Bean
    fun issueService(repository: IssueRepository) = IssueService(repository)


    @Bean
    fun boardService(
        repository: BoardRepository,
        teamService: TeamService,
        issueService: IssueService
    ) =
        BoardService(
            repository = repository,
            teamService = teamService,
            issueService = issueService
        )

}