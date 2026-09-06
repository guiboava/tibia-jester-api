package io.github.guiboava.tibiajesterapi.controller.dto.response;

import io.github.guiboava.tibiajesterapi.entity.enums.AccountStatus;
import io.github.guiboava.tibiajesterapi.entity.enums.Gender;
import io.github.guiboava.tibiajesterapi.entity.enums.Vocation;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record CharacterResponseDTO(

        UUID id,
        String name,
        Gender gender,
        String title,
        Long unlockedTitles,
        Vocation vocation,
        Integer level,
        Integer achievementPoints,
        String residence,
        String marriedTo,
        LocalDateTime lastLogin,
        AccountStatus accountStatus,
        String comment,
        String rank,
        WorldShortResponseDTO world,
        GuildResponseDTO guild,
        Set<AchievementResponseDTO> achievements,
        LocalDateTime createdDate,
        LocalDateTime updatedDate

) {

    public record WorldShortResponseDTO(
            UUID id,
            String name) {
    }

}