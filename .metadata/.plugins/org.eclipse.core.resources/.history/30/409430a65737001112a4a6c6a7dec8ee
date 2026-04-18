package com.example.demo.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import io.swagger.v3.oas.models.OpenAPI;

class SwaggerConfigTest {

    @Test
    void testCustomOpenAPI() {

        SwaggerConfig config = new SwaggerConfig();
        OpenAPI openAPI = config.customOpenAPI();

        assertNotNull(openAPI);
        assertEquals("Report Service API", openAPI.getInfo().getTitle());
        assertEquals("1.0", openAPI.getInfo().getVersion());

        assertNotNull(openAPI.getComponents().getSecuritySchemes().get("BearerAuth"));
        assertNotNull(openAPI.getSecurity());
    }
}