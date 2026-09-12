package io.github.guiboava.tibiajesterapi.controller.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record HuntLockerResponseDTO(

        UUID id,
        Boolean status,
        LocalDateTime lockedAt,
        HuntResponseDTO hunt,
        WorldResponseDTO world,
        LocalDateTime createdDate,
        LocalDateTime updatedDate

) {


}
