package io.github.guiboava.tibiajesterapi.controller.dto.request;

import jakarta.validation.constraints.NotNull;

public record UserCharacterRequestDTO(

        @NotNull
        boolean isMain

) {
}