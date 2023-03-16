package io.board.kanban.boards.adapter.repository.feign

import io.board.kanban.boards.adapter.representation.TeamResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.UUID

@FeignClient(name = "team-service", url = "\${api.teams.url}")
interface TeamFeignClient {

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): TeamResponse?

}
