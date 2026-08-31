package io.github.guiboava.tibiajesterapi.controller;

import io.github.guiboava.tibiajesterapi.controller.dto.ImageRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.ImageResponseDTO;
import io.github.guiboava.tibiajesterapi.service.ImageService;
import io.github.guiboava.tibiajesterapi.util.BytesFormatUtils;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor

@Slf4j
public class ImageController implements GenericController {

    private final ImageService service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createImage(
            @Valid @ModelAttribute ImageRequestDTO dto) {


        log.info("Imagem recebida nome: {}, tamanho: {}", dto.file().getOriginalFilename(), BytesFormatUtils.formatBytes(dto.file().getSize()));

        URI uri = generateHeaderLocation(service.save(dto));

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{imageId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Atualizar", description = "Atualizar um usuário dentro do sistema.")
    public ResponseEntity<Void> updateImage(@PathVariable UUID imageId, @Valid @ModelAttribute ImageRequestDTO dto) {

        log.info("Atualizando a imagem de id: {}", imageId);
        service.update(imageId, dto);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{imageId}")
    @Operation(summary = "Deletar.", description = "Deletar uma imagem dentro do sistema.")
    public ResponseEntity<Void> deleteImage(@PathVariable("imageId") UUID imageId) {

        log.info("Deletando a imagem de id: {}", imageId.toString());

        service.delete(imageId);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{imageId}")
    @Operation(summary = "Encontrar.", description = "Pesquisar por uma imagem dentro do sistema.")
    public ResponseEntity<byte[]> getImageById(@PathVariable("imageId") UUID imageId) {


        return service.getById(imageId);

    }

    @GetMapping
    @Operation(summary = "Listar todas imagens", description = "Mostra todas imagens salvas no sistema.")
    public ResponseEntity<Set<ImageResponseDTO>> getAllImages(){

        return ResponseEntity.ok(service.getAll());

    }


}
