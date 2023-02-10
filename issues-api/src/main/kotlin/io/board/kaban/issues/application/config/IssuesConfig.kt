package io.board.kaban.issues.application.config

import io.board.kaban.issues.adapter.mapper.IssueMapper
import io.board.kaban.issues.domain.repository.IssueRepository
import io.board.kaban.issues.domain.service.IssueService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class IssuesConfig {

    @Bean
    fun issueService(
        repository: IssueRepository,
        mapper: IssueMapper
    ): IssueService {
        return IssueService(
            repository = repository,
            mapper = mapper
        )
    }
}