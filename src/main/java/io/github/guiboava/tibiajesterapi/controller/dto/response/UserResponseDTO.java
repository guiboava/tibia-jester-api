package io.github.guiboava.tibiajesterapi.controller.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDTO(

        UUID id,
        String name,
        String email,
        String login,
        LocalDateTime createdDate,
        LocalDateTime updatedDate

) {
}
