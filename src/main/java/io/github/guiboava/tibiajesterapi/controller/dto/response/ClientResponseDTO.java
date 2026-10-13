package io.github.guiboava.tibiajesterapi.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ClienteCriado")
public record ClientResponseDTO(

        @Schema(name = "nome")
        String name,

        @Schema(name = "idCliente")
        String clientId,

        @Schema(name = "secretCliente")
        String clientSecret

) {
}