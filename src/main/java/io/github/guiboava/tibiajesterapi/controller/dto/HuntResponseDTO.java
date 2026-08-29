package io.github.guiboava.tibiajesterapi.controller.dto;

import io.github.guiboava.tibiajesterapi.entity.model.Image;

import java.util.UUID;

public record HuntResponseDTO(UUID id,
                              String name,
                              Integer levelMin,
                              Integer levelMax,
                              Image image
                              ) {
}
