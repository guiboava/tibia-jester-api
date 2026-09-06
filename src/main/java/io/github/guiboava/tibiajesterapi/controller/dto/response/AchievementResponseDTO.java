package io.github.guiboava.tibiajesterapi.controller.dto.response;

import io.github.guiboava.tibiajesterapi.entity.enums.Grade;

import java.time.LocalDateTime;
import java.util.UUID;

public record AchievementResponseDTO(

        UUID id,
        String name,
        Grade grade,
        Boolean secret,
        LocalDateTime createdDate,
        LocalDateTime updatedDate

) {
}

