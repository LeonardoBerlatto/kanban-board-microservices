package io.board.kaban.issues.domain.repository

import io.board.kaban.issues.domain.entity.Issue
import java.util.Optional
import java.util.UUID

interface IssueRepository {
    fun save(issue: Issue): Issue
    fun findById(id: UUID): Optional<Issue>
    fun delete(issue: Issue)
}
