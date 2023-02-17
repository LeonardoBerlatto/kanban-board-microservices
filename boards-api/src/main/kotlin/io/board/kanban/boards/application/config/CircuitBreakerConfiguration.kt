package io.board.kanban.boards.application.config

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory
import org.springframework.cloud.client.circuitbreaker.Customizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
class CircuitBreakerConfiguration {

    @Bean
    fun circuitBreakerFactoryCustomizer(): Customizer<Resilience4JCircuitBreakerFactory> {
        val config = CircuitBreakerConfig.custom()
            .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
            .slidingWindowSize(10)
            .slowCallRateThreshold(70f)
            .failureRateThreshold(50f)
            .waitDurationInOpenState(Duration.ofSeconds(2))
            .slowCallDurationThreshold(Duration.ofSeconds(5))
            .permittedNumberOfCallsInHalfOpenState(5)
            .build()

        return Customizer { factory: Resilience4JCircuitBreakerFactory ->
            factory.configure({
                it.circuitBreakerConfig(config)
            })
        }
    }
}