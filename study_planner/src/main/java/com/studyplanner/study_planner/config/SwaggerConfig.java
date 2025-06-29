package com.studyplanner.study_planner.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI studyPlannerOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Smart Study Planner API")
                        .description("Spring Boot REST API for Study Planning & Scheduling")
                        .version("1.0"));
    }
}
