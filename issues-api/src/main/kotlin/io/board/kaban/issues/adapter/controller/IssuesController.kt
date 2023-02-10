package io.board.kaban.issues.adapter.controller

import io.board.kaban.issues.adapter.mapper.IssueMapper
import io.board.kaban.issues.adapter.representation.IssueRequest
import io.board.kaban.issues.adapter.representation.IssueResponse
import io.board.kaban.issues.domain.service.IssueService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/issues")
class IssuesController(
    val service: IssueService,
    val mapper: IssueMapper
) {

    @PostMapping
    fun create(@Valid @RequestBody request: IssueRequest): ResponseEntity<IssueResponse> {
        val response = mapper.toResponse(service.create(request))

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response)
    }
}