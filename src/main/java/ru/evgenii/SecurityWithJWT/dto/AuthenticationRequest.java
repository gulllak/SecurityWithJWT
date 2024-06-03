package ru.evgenii.SecurityWithJWT.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "AuthenticationRequest", description = "Данные для аутентификации пользователя")
public class AuthenticationRequest {
    @Schema(description = "Имя пользователя", example = "test")
    private String username;
    @Schema(description = "Пароль пользователя", example = "myPassword")
    private String password;
}
