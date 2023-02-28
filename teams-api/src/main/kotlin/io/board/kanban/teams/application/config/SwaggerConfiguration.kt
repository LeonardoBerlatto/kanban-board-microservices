package io.board.kanban.teams.application.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfiguration {

    @Bean
    fun openAPI(): OpenAPI = OpenAPI()
        .info(
            Info().title("Teams API")
                .description("Teams API for Kanban Boards Documentation")
                .version("v1.0.0")
        )
}
