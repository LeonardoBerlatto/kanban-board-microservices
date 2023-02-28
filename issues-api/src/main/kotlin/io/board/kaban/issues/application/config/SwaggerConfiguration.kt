package io.board.kaban.issues.application.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfiguration {

    @Bean
    fun openAPI(): OpenAPI = OpenAPI()
        .info(
            Info().title("Issues API")
                .description("Issues API for Kanban Board Documentation")
                .version("v1.0.0")
        )
}
