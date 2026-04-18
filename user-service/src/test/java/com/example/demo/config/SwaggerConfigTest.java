package com.example.demo.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import io.swagger.v3.oas.models.OpenAPI;

class SwaggerConfigTest {

    @Test
    void testSwagger() {
        SwaggerConfig config = new SwaggerConfig();
        OpenAPI api = config.customOpenAPI();

        assertEquals("User Service API", api.getInfo().getTitle());
        assertEquals("1.0", api.getInfo().getVersion());
        assertNotNull(api.getComponents().getSecuritySchemes().get("BearerAuth"));
    }
}