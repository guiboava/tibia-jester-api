package io.github.guiboava.tibiajesterapi.controller.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record HuntLockerRequestDTO(

        @NotNull(message = "O status do hunt locker é obrigatório")
        Boolean status,

        @NotNull(message = "A data/hora de bloqueio é obrigatória")
        LocalDateTime lockedAt,

        @NotNull(message = "O id do hunt é obrigatório")
        UUID huntId,

        @NotNull(message = "O id do world é obrigatório")
        UUID worldId

) {
}
