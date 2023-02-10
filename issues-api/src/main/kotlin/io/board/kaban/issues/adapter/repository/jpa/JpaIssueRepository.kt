package io.board.kaban.issues.adapter.repository.jpa

import io.board.kaban.issues.domain.entity.Issue
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface JpaIssueRepository: CrudRepository<Issue, UUID> {

}
