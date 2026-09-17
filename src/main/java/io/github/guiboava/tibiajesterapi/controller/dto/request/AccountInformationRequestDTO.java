package io.github.guiboava.tibiajesterapi.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public record AccountInformationRequestDTO(

        @NotNull(message = "Data de criação da conta é obrigatória")
        @PastOrPresent(message = "Data de criação da conta não pode ser futura")
        LocalDateTime createdAccountDate,

        @NotBlank(message = "Título de lealdade é obrigatório")
        String loyaltyTitle

) {
}
