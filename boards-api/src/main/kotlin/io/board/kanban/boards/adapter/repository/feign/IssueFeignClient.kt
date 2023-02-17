package io.board.kanban.boards.adapter.repository.feign

import io.board.kanban.boards.adapter.representation.IssueResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.UUID

@FeignClient(name = "issue-service", url = "\${api.issue.url}")
interface IssueFeignClient {

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): IssueResponse?

}
