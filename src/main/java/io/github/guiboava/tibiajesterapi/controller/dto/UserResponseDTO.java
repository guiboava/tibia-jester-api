package io.github.guiboava.tibiajesterapi.controller.dto;

import java.util.UUID;

public record UserResponseDTO(UUID id,
                              String name,
                              String email,
                              String login) {
}
