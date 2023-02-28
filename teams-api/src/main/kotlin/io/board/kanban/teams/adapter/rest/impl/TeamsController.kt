package io.board.kanban.teams.adapter.rest.impl

import io.board.kanban.teams.adapter.mapper.TeamMapper
import io.board.kanban.teams.adapter.representation.CreateTeamRequest
import io.board.kanban.teams.adapter.representation.TeamResponse
import io.board.kanban.teams.adapter.rest.TeamsApi
import io.board.kanban.teams.domain.service.TeamService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID


@RestController
@RequestMapping("/teams")
class TeamsController(
    val service: TeamService,
    val mapper: TeamMapper
): TeamsApi {

    @GetMapping("/{id}")
    override fun getById(@PathVariable id: UUID): TeamResponse {
        val team = service.findById(id)
        return mapper.toResponse(team)
    }

    @GetMapping
    override fun getByName(
        @RequestParam name: String
    ): Page<TeamResponse> {
        val teams = service.findTeamByName(name, Pageable.unpaged())
        return mapper.toResponse(teams)
    }

    @PostMapping
    override fun create(@RequestBody request: CreateTeamRequest): ResponseEntity<TeamResponse> {
        val team = service.create(request)
        return ResponseEntity(mapper.toResponse(team), HttpStatus.CREATED)
    }

}