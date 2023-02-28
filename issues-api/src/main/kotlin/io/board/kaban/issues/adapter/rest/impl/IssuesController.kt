package io.board.kaban.issues.adapter.rest.impl

import io.board.kaban.issues.adapter.mapper.IssueMapper
import io.board.kaban.issues.adapter.representation.IssueRequest
import io.board.kaban.issues.adapter.representation.IssueResponse
import io.board.kaban.issues.adapter.rest.IssuesApi
import io.board.kaban.issues.domain.service.IssueService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/issues")
class IssuesController(
    val service: IssueService,
    val mapper: IssueMapper
): IssuesApi {

    @GetMapping("/{id}")
    override fun getById(@PathVariable id: UUID): ResponseEntity<IssueResponse> {
        val response = mapper.toResponse(service.findById(id))

        return ResponseEntity.ok(response)
    }

    @PostMapping
    override fun create(@Valid @RequestBody request: IssueRequest): ResponseEntity<IssueResponse> {
        val response = mapper.toResponse(service.create(request))

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response)
    }

    @PutMapping("/{id}")
    override fun update(@PathVariable id: UUID,
                        @Valid @RequestBody request: IssueRequest
    ): ResponseEntity<IssueResponse> {
        val response = mapper.toResponse(service.update(id, request))

        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    override fun delete(@PathVariable id: UUID): ResponseEntity<Void> {
        service.inactivate(id)
        return ResponseEntity
            .noContent()
            .build()
    }
}