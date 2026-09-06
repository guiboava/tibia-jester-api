package io.github.guiboava.tibiajesterapi.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public record BossRequestDTO(

        @NotBlank
        String name

) {
}