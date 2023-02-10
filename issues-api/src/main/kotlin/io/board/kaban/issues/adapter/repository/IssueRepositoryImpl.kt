package io.board.kaban.issues.adapter.repository

import io.board.kaban.issues.adapter.repository.jpa.JpaIssueRepository
import io.board.kaban.issues.domain.entity.Issue
import io.board.kaban.issues.domain.repository.IssueRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
class IssueRepositoryImpl(
    private val jpaRepository: JpaIssueRepository
) : IssueRepository {

    override fun save(issue: Issue): Issue {
        return jpaRepository.save(issue)
    }

    override fun findById(id: UUID): Optional<Issue> {
        return jpaRepository.findById(id)
    }
}