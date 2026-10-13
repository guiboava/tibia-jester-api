package io.github.guiboava.tibiajesterapi.controller;

import io.github.guiboava.tibiajesterapi.controller.dto.request.HuntRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.HuntResponseDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.request.ImageRequestDTO;
import io.github.guiboava.tibiajesterapi.service.HuntService;
import io.github.guiboava.tibiajesterapi.util.BytesFormatUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;
import java.util.UUID;

@RestController()
@RequestMapping("/hunts")
@RequiredArgsConstructor
@Tag(name = "Hunts")
@Slf4j
public class HuntController implements GenericController{

    private final HuntService service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> createHunt(@Valid HuntRequestDTO huntDto, @Valid @ModelAttribute ImageRequestDTO imageDto) {

        log.info("Cadastrando uma nova Hunt: {}", huntDto.name());

        if (imageDto == null || imageDto.file() == null || imageDto.file().isEmpty()) {
            log.info("Requisição sem imagem.");
        } else {
            log.info(
                    "Imagem recebida. Nome: {}, tamanho: {}", imageDto.file().getOriginalFilename(), BytesFormatUtils.formatBytes(imageDto.file().getSize())
            );
        }

        URI uri = generateHeaderLocation((service.save(huntDto, imageDto)));

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{huntId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Atualizar", description = "Atualizar uma hunt dentro do sistema.")
    public ResponseEntity<Void> updateHunt(@PathVariable UUID huntId, @Valid HuntRequestDTO huntDto, @Valid @ModelAttribute ImageRequestDTO imageDto) {

        log.info("Atualizando a hunt de id: {}", huntId);
        service.update(huntId, huntDto, imageDto);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{huntId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Deletar.", description = "Deletar uma hunt dentro do sistema.")
    public ResponseEntity<Void> deleteHunt(@PathVariable("huntId") UUID huntId) {

        log.info("Deletando a hunt de id: {}", huntId.toString());

        service.delete(huntId);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{huntId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER','VIP')")
    @Operation(summary = "Encontrar.", description = "Pesquisar por uma hunt dentro do sistema.")
    public ResponseEntity<HuntResponseDTO> getHuntById(@PathVariable("huntId") UUID huntId) {

        return ResponseEntity.ok(service.getById(huntId));

    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER','VIP')")
    @Operation(summary = "Listar todas hunts.", description = "Mostra todas hunts salvas no sistema.")
    public ResponseEntity<Set<HuntResponseDTO>> getAllHunts() {

        return ResponseEntity.ok(service.getAll());

    }


}
