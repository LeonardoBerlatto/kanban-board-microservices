package io.board.kanban.teamsapi.controller

import io.board.kanban.teamsapi.mapper.MemberMapper
import io.board.kanban.teamsapi.representation.CreateMemberRequest
import io.board.kanban.teamsapi.representation.MemberResponse
import io.board.kanban.teamsapi.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class MemberController(val memberService: MemberService, val mapper: MemberMapper) {

    @PostMapping
    fun create(@RequestBody request: CreateMemberRequest): ResponseEntity<MemberResponse> {
        val response = mapper.toResponse(memberService.create(request))

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(response)
    }
}