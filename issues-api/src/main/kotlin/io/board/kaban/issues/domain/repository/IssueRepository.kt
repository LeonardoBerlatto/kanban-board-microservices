package io.board.kaban.issues.domain.repository

import io.board.kaban.issues.domain.entity.Issue

interface IssueRepository {
    fun save(issue: Issue): Issue
}
