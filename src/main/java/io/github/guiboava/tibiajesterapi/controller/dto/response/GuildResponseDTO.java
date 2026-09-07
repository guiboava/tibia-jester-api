package io.github.guiboava.tibiajesterapi.controller.dto.response;

import java.util.UUID;

public record GuildResponseDTO(
        UUID id,
        String name
) {
}