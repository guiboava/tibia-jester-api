package io.github.guiboava.tibiajesterapi.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(name = "Login")
public record LoginRequestDTO(

        @NotNull(message = "Username é obrigatório")
        String username,

        @NotNull(message = "Senha é obrigatória")
        String password

) {
}
