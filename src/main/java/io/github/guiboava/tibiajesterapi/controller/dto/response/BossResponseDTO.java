package io.github.guiboava.tibiajesterapi.controller.dto.response;

import java.util.UUID;

public record BossResponseDTO(UUID id,
                              String name,
                              ImageResponseDTO image
) {
}
