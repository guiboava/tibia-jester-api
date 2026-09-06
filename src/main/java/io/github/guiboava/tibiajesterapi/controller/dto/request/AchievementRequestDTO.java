package io.github.guiboava.tibiajesterapi.controller.dto.request;

import io.github.guiboava.tibiajesterapi.entity.enums.Grade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AchievementRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
        String name,

        @NotNull(message = "Grade é obrigatória")
        Grade grade,

        @NotNull(message = "Secret é obrigatório")
        Boolean secret

) {
}