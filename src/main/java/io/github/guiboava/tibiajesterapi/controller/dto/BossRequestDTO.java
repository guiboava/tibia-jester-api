package io.github.guiboava.tibiajesterapi.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record BossRequestDTO(

        @NotBlank
        String name

) {
}