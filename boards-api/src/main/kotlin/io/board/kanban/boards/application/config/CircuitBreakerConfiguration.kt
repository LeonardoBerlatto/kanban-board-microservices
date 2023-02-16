package io.board.kanban.boards.application.config

import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
class CircuitBreakerConfiguration {

    @Bean
    fun circuitBreakerConfiguration() = CircuitBreakerConfig.custom()
        .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
        .slidingWindowSize(10)
        .slowCallRateThreshold(70f)
        .failureRateThreshold(50f)
        .waitDurationInOpenState(Duration.ofSeconds(2))
        .slowCallDurationThreshold(Duration.ofSeconds(2))
        .permittedNumberOfCallsInHalfOpenState(5)
        .build()

    @Bean
    fun teamServiceCircuitBreaker() = CircuitBreaker.of(
        "circuit-breaker-team-service",
        circuitBreakerConfiguration()
    )

    @Bean
    fun issueServiceCircuitBreaker() = CircuitBreaker.of(
        "circuit-breaker-issue-service",
        circuitBreakerConfiguration()
    )

}