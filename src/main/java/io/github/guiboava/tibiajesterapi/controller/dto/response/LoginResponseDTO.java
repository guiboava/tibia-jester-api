package io.github.guiboava.tibiajesterapi.controller.dto.response;

import java.util.UUID;

public record LoginResponseDTO(
        String token,
        String refreshToken,
        String username,
        UUID userId,
        String email,
        Long expiresIn
) {
}
