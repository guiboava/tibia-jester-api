package io.github.guiboava.tibiajesterapi.controller.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record AccountInformationResponseDTO(

        UUID id,
        LocalDateTime createdAccountDate,
        String loyaltyTitle,
        LocalDateTime createdDate,
        LocalDateTime updatedDate

) {
}
