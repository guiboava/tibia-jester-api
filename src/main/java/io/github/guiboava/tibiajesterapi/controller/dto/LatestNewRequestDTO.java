package io.github.guiboava.tibiajesterapi.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Schema(name = "Mundo")
public record LatestNewRequestDTO(

        @NotNull(message = "O id do TibiaData é obrigatório.")
        @Min(value = 0, message = "O id do TibiaData não pode ser negativo.")
        @Schema(name = "tibiaDataId")
        Long tibiaDataId,

        @NotNull(message = "A data de lançamento é obrigatória.")
        @Schema(name = "dataLancamento")
        LocalDate releaseDate,

        @NotBlank(message = "O título é obrigatório.")
        @Size(min = 3, max = 255, message = "O título deve ter entre 3 e 255 caracteres.")
        @Schema(name = "titulo")
        String newTitle,

        @NotBlank(message = "A categoria é obrigatória.")
        @Size(max = 100, message = "A categoria deve ter no máximo 100 caracteres.")
        @Schema(name = "categoria")
        String category,

        @NotBlank(message = "O tipo é obrigatório.")
        @Size(max = 100, message = "O tipo deve ter no máximo 100 caracteres.")
        @Schema(name = "tipo")
        String type,

        @NotBlank(message = "A URL do Tibia é obrigatória.")
        @Size(max = 500, message = "A URL do Tibia deve ter no máximo 500 caracteres.")
        @Schema(name = "urlTibia")
        String urlTibia,

        @NotBlank(message = "A URL da API é obrigatória.")
        @Size(max = 500, message = "A URL da API deve ter no máximo 500 caracteres.")
        @Schema(name = "urlApi")
        String urlApi,

        @Schema(name = "conteudo")
        String content

) {

}
