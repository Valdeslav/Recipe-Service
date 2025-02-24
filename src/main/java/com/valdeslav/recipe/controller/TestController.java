package com.valdeslav.recipe.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/test")
@Tag(name = "Test Controller", description = "Контроллер для тестирования доступа к сервису")
public class TestController {

    @Operation(
            summary = "Тестирование доступа",
            description = "Эндпоинт для проверки доступа к сервису рецептов"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Успешный доступ к сервису"
    )
    @GetMapping("/access")
    public String testAccess() {
        return "You have access!";
    }
}
