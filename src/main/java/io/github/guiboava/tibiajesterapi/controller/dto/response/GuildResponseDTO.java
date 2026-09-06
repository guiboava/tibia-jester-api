package io.github.guiboava.tibiajesterapi.controller.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record GuildResponseDTO(

        UUID id,
        String name,
        Integer memberCount,
        LocalDateTime createdDate,
        LocalDateTime updatedDate

) {
}