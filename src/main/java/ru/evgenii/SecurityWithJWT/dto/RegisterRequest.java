package ru.evgenii.SecurityWithJWT.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.evgenii.SecurityWithJWT.util.Role;
import io.swagger.v3.oas.annotations.media.Schema;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "RegisterRequest", description = "Данные для регистрации нового пользователя")
public class RegisterRequest {
    @Schema(description = "Имя пользователя", example = "test")
    private String username;
    @Schema(description = "Пароль пользователя", example = "myPassword")
    private String password;
    @Schema(description = "Роль пользователя", example = "USER")
    private Role role;
}
