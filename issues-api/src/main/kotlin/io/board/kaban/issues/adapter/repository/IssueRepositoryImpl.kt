package io.board.kaban.issues.adapter.repository

import io.board.kaban.issues.adapter.repository.jpa.JpaIssueRepository
import io.board.kaban.issues.domain.entity.Issue
import io.board.kaban.issues.domain.repository.IssueRepository
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class IssueRepositoryImpl(
    private val jpaRepository: JpaIssueRepository,
    private val rabbitTemplate: RabbitTemplate
) : IssueRepository {

    override fun save(issue: Issue): Issue {
        return jpaRepository.save(issue)
    }

    override fun findById(id: UUID): Optional<Issue> {
        return jpaRepository.findById(id)
    }

    override fun delete(issue: Issue) {
        issue.active = false
        jpaRepository.save(issue)
        rabbitTemplate.convertAndSend("issue-exchange", "issue.inactive", issue)
    }
}