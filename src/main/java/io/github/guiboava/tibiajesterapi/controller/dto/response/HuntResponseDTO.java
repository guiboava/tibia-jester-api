package io.github.guiboava.tibiajesterapi.controller.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record HuntResponseDTO(

        UUID id,
        String name,
        Integer levelMin,
        Integer levelMax,
        ImageResponseDTO image,
        LocalDateTime createdDate,
        LocalDateTime updatedDate

) {
}
