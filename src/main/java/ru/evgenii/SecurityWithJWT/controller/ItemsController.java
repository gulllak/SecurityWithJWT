package ru.evgenii.SecurityWithJWT.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.evgenii.SecurityWithJWT.model.Item;
import ru.evgenii.SecurityWithJWT.repository.ItemRepository;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemsController {
    private final ItemRepository itemRepository;

    @Operation(summary = "Защищенный эндпоинт для USER и ADMIN",
            description = "Возвращает строку",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешное выполнение"),
                    @ApiResponse(responseCode = "403", description = "В доступе отказано")
            })
    @GetMapping
    public ResponseEntity<String> getItem() {
        return ResponseEntity.ok("Ответ из защищенного эндпоинта!");
    }

    @Operation(summary = "Защищенный эндпоинт для ADMIN",
            description = "Создает новый предмет",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Успешное выполнение", content = @Content(schema = @Schema(implementation = Item.class))),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен")
            })
    @PostMapping
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemRepository.save(item));
    }
}
