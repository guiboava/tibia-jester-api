package io.github.guiboava.tibiajesterapi.controller.dto;

import java.util.UUID;

public record BossResponseDTO(UUID id,
                              String name,
                              ImageResponseDTO image
) {
}
