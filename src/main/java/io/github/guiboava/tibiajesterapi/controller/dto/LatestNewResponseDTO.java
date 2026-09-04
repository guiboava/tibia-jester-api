package io.github.guiboava.tibiajesterapi.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Schema(name = "Mundo")
public record LatestNewResponseDTO(

        @Schema(name = "id")
        UUID id,

        @Schema(name = "tibiaDataId")
        Long tibiaDataId,

        @Schema(name = "dataLancamento")
        LocalDate releaseDate,

        @Schema(name = "titulo")
        String newTitle,

        @Schema(name = "categoria")
        String category,

        @Schema(name = "tipo")
        String type,

        @Schema(name = "urlTibia")
        String urlTibia,

        @Schema(name = "urlApi")
        String urlApi,

        @Schema(name = "conteudo")
        String content,

        @Schema(name = "dataCriacao")
        LocalDateTime createdDate,

        @Schema(name = "dataAtualizacao")
        LocalDateTime updatedDate
) {

}