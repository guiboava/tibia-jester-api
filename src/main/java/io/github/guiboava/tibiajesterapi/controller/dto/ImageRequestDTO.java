package io.github.guiboava.tibiajesterapi.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;

@Schema(name = "Imagem")
public record ImageRequestDTO(
        String name,
        String extension,
        Long size,
        MultipartFile file
) {
}