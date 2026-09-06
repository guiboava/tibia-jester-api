package io.github.guiboava.tibiajesterapi.controller.dto.response;

import java.util.UUID;

public record UserResponseDTO(UUID id,
                              String name,
                              String email,
                              String login) {
}
