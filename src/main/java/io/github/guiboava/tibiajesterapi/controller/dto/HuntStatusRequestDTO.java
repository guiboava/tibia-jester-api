package io.github.guiboava.tibiajesterapi.controller.dto;

import io.github.guiboava.tibiajesterapi.entity.enums.Party;
import io.github.guiboava.tibiajesterapi.entity.enums.Vocation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Schema(name = "HuntStatusRequest")
public record HuntStatusRequestDTO(

        @NotNull(message = "A vocação é obrigatória.")
        @Schema(name = "vocacao")
        Vocation vocation,

        @NotNull(message = "O tipo de party é obrigatório.")
        @Schema(name = "party")
        Party party,

        @NotNull(message = "A experiência bruta é obrigatória.")
        @Min(value = 0, message = "A experiência bruta não pode ser negativa.")
        @Schema(name = "xpBruto")
        Long rawXp,

        @NotNull(message = "A experiência por hora é obrigatória.")
        @Min(value = 0, message = "A experiência por hora não pode ser negativa.")
        @Schema(name = "xpPorHora")
        Long xpPerHour

) {

}
