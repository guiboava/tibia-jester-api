package io.github.guiboava.tibiajesterapi.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Schema(name = "Cliente")
public record ClientRequestDTO(

        @NotNull
        @Length(max = 150)
        @Schema(name = "nome")
        String name,

        @Length(max = 200)
        @Schema(name = "linkRedirecionamento")
        String redirectURI,

        @Length(max = 50)
        @Schema(name = "scope")
        String scope

) {
}