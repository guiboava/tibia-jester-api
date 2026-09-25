package io.github.guiboava.tibiajesterapi.controller.dto.response;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserCharacterResponseDTO(

        UUID userId,
        UUID characterId,
        Boolean isMain

) {
}