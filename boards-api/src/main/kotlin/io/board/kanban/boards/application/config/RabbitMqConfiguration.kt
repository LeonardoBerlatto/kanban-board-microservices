package io.board.kanban.boards.application.config

import io.board.kanban.boards.adapter.broker.rabbitmq.DeleteIssueListener
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

const val ISSUE_EXCHANGE = "issue-exchange"
const val ISSUE_QUEUE_NAME = "issue"

@Configuration
class RabbitMqConfiguration {

    @Bean
    fun queue(): Queue = Queue(ISSUE_QUEUE_NAME, true)

    @Bean
    fun exchange(): TopicExchange = TopicExchange(ISSUE_EXCHANGE)

    @Bean
    fun binding(queue: Queue?, exchange: TopicExchange?): Binding = BindingBuilder
        .bind(queue)
        .to(exchange)
        .with("$ISSUE_QUEUE_NAME.inactive")

    @Bean
    fun container(
        connectionFactory: ConnectionFactory?,
        listenerAdapter: MessageListenerAdapter?
    ): SimpleMessageListenerContainer {
        val container = SimpleMessageListenerContainer()
        container.connectionFactory = connectionFactory!!
        container.setQueueNames(ISSUE_QUEUE_NAME)
        container.setMessageListener(listenerAdapter!!)
        return container
    }

    @Bean
    fun listenerAdapter(listener: DeleteIssueListener?): MessageListenerAdapter =
        MessageListenerAdapter(listener, "receiveMessage")

}