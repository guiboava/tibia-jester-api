package io.github.guiboava.tibiajesterapi.controller.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record BossResponseDTO(
        UUID id,
        String name,
        ImageResponseDTO image,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
) {
}
