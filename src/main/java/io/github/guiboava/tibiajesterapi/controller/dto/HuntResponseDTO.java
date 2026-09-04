package io.github.guiboava.tibiajesterapi.controller.dto;

import java.util.UUID;

public record HuntResponseDTO(UUID id,
                              String name,
                              Integer levelMin,
                              Integer levelMax,
                              ImageResponseDTO image
                              ) {
}
