package io.github.guiboava.tibiajesterapi.controller.dto;

import io.github.guiboava.tibiajesterapi.entity.enums.Party;
import io.github.guiboava.tibiajesterapi.entity.enums.Vocation;
import io.github.guiboava.tibiajesterapi.entity.model.Hunt;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(name = "HuntStatusResponse")
public record HuntStatusResponseDTO(

        @Schema(name = "id")
        UUID id,

        @Schema(name = "vocacao")
        Vocation vocation,

        @Schema(name = "party")
        Party party,

        @Schema(name = "xpBruto")
        Long rawXp,

        @Schema(name = "xpPorHora")
        Long xpPerHour,

        @Schema(name = "Hunt")
        UUID huntId,

        @Schema(name = "dataCriacao")
        LocalDateTime createdDate,

        @Schema(name = "dataAtualizacao")
        LocalDateTime updatedDate

) {

}
