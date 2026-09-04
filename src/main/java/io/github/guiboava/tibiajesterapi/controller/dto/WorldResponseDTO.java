package io.github.guiboava.tibiajesterapi.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Schema(name = "Mundo")
public record WorldResponseDTO(

        @Schema(name = "id")
        UUID id,

        @Schema(name = "nome")
        String name,

        @Schema(name = "status")
        String status,

        @Schema(name = "jogadoresOnline")
        Integer playersOnline,

        @Schema(name = "tipoPvP")
        String pvpType,

        @Schema(name = "somentePremium")
        Boolean premiumOnly,

        @Schema(name = "tipoTransferencia")
        String transferType,

        @Schema(name = "protegidoBattleEye")
        Boolean battleyeProtected,

        @Schema(name = "dataBattleEye")
        LocalDate battleyeDate,

        @Schema(name = "tipoMundo")
        String gameWorldType,

        @Schema(name = "dataCriacao")
        LocalDateTime createdDate,

        @Schema(name = "dataAtualizacao")
        LocalDateTime updatedDate
) {

}