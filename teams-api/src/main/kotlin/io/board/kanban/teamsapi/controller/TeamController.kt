package io.board.kanban.teamsapi.controller

import io.board.kanban.teamsapi.mapper.TeamMapper
import io.board.kanban.teamsapi.representation.CreateTeamRequest
import io.board.kanban.teamsapi.representation.TeamResponse
import io.board.kanban.teamsapi.service.TeamService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/teams")
class TeamController(
    val service: TeamService,
    val mapper: TeamMapper
) {

    @PostMapping
    fun create(@RequestBody request: CreateTeamRequest): ResponseEntity<TeamResponse> {
        val team = service.create(request)
        return ResponseEntity(mapper.toResponse(team), HttpStatus.CREATED)
    }

    @GetMapping
    fun getByName(
        @RequestParam name: String
    ): ResponseEntity<Page<TeamResponse>> {
        val teams = service.findTeamByName(name, Pageable.unpaged())
        return ResponseEntity(mapper.toResponse(teams), HttpStatus.CREATED)
    }
}