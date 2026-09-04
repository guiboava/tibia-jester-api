package io.github.guiboava.tibiajesterapi.controller.dto;

import io.github.guiboava.tibiajesterapi.entity.enums.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Schema(name = "Mundo")
public record WorldRequestDTO(

        @NotBlank(message = "O nome é obrigatório.")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
        @Schema(name = "nome")
        String name,

        @NotBlank(message = "O status é obrigatório.")
        @Size(max = 20, message = "O status deve ter no máximo 20 caracteres.")
        @Schema(name = "status")
        String status,

        @NotNull(message = "A quantidade de jogadores online é obrigatória.")
        @Min(value = 0, message = "A quantidade de jogadores online não pode ser negativa.")
        @Schema(name = "jogadoresOnline")
        Integer playersOnline,

        @Size(max = 50, message = "O tipo de PvP deve ter no máximo 50 caracteres.")
        @Schema(name = "tipoPvP")
        String pvpType,

        @NotNull(message = "A informação de mundo premium é obrigatória.")
        @Schema(name = "somentePremium")
        Boolean premiumOnly,

        @Size(max = 30, message = "O tipo de transferência deve ter no máximo 30 caracteres.")
        @Schema(name = "tipoTransferencia")
        String transferType,

        @NotNull(message = "A informação de proteção BattleEye é obrigatória.")
        @Schema(name = "protegidoBattleEye")
        Boolean battleyeProtected,

        @Schema(name = "dataBattleEye")
        LocalDate battleyeDate,

        @Size(max = 30, message = "O tipo de mundo deve ter no máximo 30 caracteres.")
        @Schema(name = "tipoMundo")
        String gameWorldType
) {

}
