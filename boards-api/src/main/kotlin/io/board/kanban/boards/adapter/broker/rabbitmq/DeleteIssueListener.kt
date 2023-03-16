package io.board.kanban.boards.adapter.broker.rabbitmq

import com.fasterxml.jackson.databind.ObjectMapper
import io.board.kanban.boards.adapter.representation.IssueResponse
import io.board.kanban.boards.domain.service.BoardService
import mu.KotlinLogging
import org.springframework.stereotype.Component


@Component
class DeleteIssueListener(
    private val service: BoardService,
    private val mapper: ObjectMapper
) {

    private val logger = KotlinLogging.logger {}

    fun receiveMessage(message: String) {
        var issue: IssueResponse? = null;

        try {
            issue = mapper.readValue(message, IssueResponse::class.java)
        } catch (exception: Exception) {
            logger.error { "Invalid issue id: $message" }
        }

        service.removeIssueFromBoards(issueId = issue?.id)
    }


}
