package io.github.guiboava.tibiajesterapi.controller.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record ImageResponseDTO(

        UUID id,
        String url,
        LocalDateTime createdDate,
        LocalDateTime updatedDate

) {


}