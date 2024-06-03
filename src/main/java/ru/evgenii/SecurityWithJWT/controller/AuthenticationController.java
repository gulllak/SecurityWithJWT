package ru.evgenii.SecurityWithJWT.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.evgenii.SecurityWithJWT.dto.ApiError;
import ru.evgenii.SecurityWithJWT.dto.AuthenticationRequest;
import ru.evgenii.SecurityWithJWT.dto.AuthenticationResponse;
import ru.evgenii.SecurityWithJWT.dto.RegisterRequest;
import ru.evgenii.SecurityWithJWT.service.AuthenticationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Регистриция нового пользователя",
            description = "Возвращает AuthenticationResponse содержащий JWTToken",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешное выполнение", content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))),
                    @ApiResponse(responseCode = "403", description = "Пользователь с таким именем уже существует", content = @Content(schema = @Schema(implementation = ApiError.class)))
            })
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @Operation(summary = "Аутентификация зарегистрированного пользователя",
            description = "Возвращает AuthenticationResponse содержащий JWTToken",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешное выполнение", content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))),
                    @ApiResponse(responseCode = "403", description = "Неверный username/password", content = @Content(schema = @Schema(implementation = ApiError.class))),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден.", content = @Content(schema = @Schema(implementation = ApiError.class)))
            })
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
