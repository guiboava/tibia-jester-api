package io.github.guiboava.tibiajesterapi.controller.dto.request;

import io.github.guiboava.tibiajesterapi.entity.enums.AccountStatus;
import io.github.guiboava.tibiajesterapi.entity.enums.Gender;
import io.github.guiboava.tibiajesterapi.entity.enums.Vocation;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record CharacterRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
        String name,

        @NotNull(message = "Gênero é obrigatório")
        Gender gender,

        @NotBlank(message = "Título é obrigatório")
        String title,

        @NotNull(message = "Títulos desbloqueados é obrigatório")
        @PositiveOrZero(message = "Títulos desbloqueados não pode ser negativo")
        Long unlockedTitles,

        @NotNull(message = "Vocação é obrigatória")
        Vocation vocation,

        @NotNull(message = "Level é obrigatório")
        @Positive(message = "Level deve ser positivo")
        Integer level,

        @NotNull(message = "Pontos de conquista são obrigatórios")
        @PositiveOrZero(message = "Pontos de conquista não pode ser negativo")
        Integer achievementPoints,

        @NotBlank(message = "Residência é obrigatória")
        String residence,

        String marriedTo,

        @NotNull(message = "Último login é obrigatório")
        LocalDateTime lastLogin,

        @NotNull(message = "Status da conta é obrigatório")
        AccountStatus accountStatus,

        String comment,

        String rank,

        @NotNull(message = "ID do mundo é obrigatório")
        UUID worldId,

        UUID accountInformationId,

        UUID guildId,

        Set<UUID> achievementIds

) {
}