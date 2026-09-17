package io.github.guiboava.tibiajesterapi.controller.dto.request;

import jakarta.validation.constraints.NotEmpty;

import java.util.Set;
import java.util.UUID;

public record BossAlarmRequestDTO(

        @NotEmpty(message = "É necessário informar ao menos um boss")
        Set<UUID> bossIds
) {
}
