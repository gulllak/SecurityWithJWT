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
public class AuthenticationResponse {
    @Schema(description = "Сгенерировнный токен", example = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLC.9eD7qSOruR64NAzGYfZdotqUM6")
    private String token;
}
