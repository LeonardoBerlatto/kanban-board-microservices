package io.board.kanban.teamsapi.controller

import io.board.kanban.teamsapi.mapper.MemberMapper
import io.board.kanban.teamsapi.representation.MemberRequest
import io.board.kanban.teamsapi.representation.MemberResponse
import io.board.kanban.teamsapi.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/members")
class MemberController(val memberService: MemberService, val mapper: MemberMapper) {

    @PostMapping
    fun create(@RequestBody request: MemberRequest): ResponseEntity<MemberResponse> {
        val response = mapper.toResponse(memberService.create(request))

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response)
    }

    @PutMapping
    fun update(@RequestBody request: MemberRequest): ResponseEntity<MemberResponse> {
        val response = mapper.toResponse(memberService.update(request))

        return ResponseEntity.ok(response)
    }


    @DeleteMapping("/user/{userId}/team/{teamId}")
    fun remove(@PathVariable userId: UUID, @PathVariable teamId: UUID): ResponseEntity<Void> {
        memberService.remove(userId, teamId)
        return ResponseEntity
            .noContent()
            .build()
    }

}