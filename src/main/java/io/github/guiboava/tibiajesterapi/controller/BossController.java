package io.github.guiboava.tibiajesterapi.controller;

import io.github.guiboava.tibiajesterapi.controller.dto.BossRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.BossResponseDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.ImageRequestDTO;
import io.github.guiboava.tibiajesterapi.service.BossService;
import io.github.guiboava.tibiajesterapi.util.BytesFormatUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;
import java.util.UUID;

@RestController()
@RequestMapping("/bosses")
@RequiredArgsConstructor
@Tag(name = "Bosses")
@Slf4j
public class BossController implements GenericController {

    private final BossService service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createBoss(@Valid BossRequestDTO bossDto, @Valid @ModelAttribute ImageRequestDTO imageDto) {

        log.info("Cadastrando uma nova Boss: {}", bossDto.name());

        if (imageDto == null || imageDto.file() == null || imageDto.file().isEmpty()) {
            log.info("Requisição sem imagem.");
        } else {
            log.info(
                    "Imagem recebida. Nome: {}, tamanho: {}", imageDto.file().getOriginalFilename(), BytesFormatUtils.formatBytes(imageDto.file().getSize())
            );
        }

        URI uri = generateHeaderLocation((service.save(bossDto, imageDto)));

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{bossId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Atualizar", description = "Atualizar uma boss dentro do sistema.")
    public ResponseEntity<Void> updateBoss(@PathVariable UUID bossId, @Valid BossRequestDTO bossDto, @Valid @ModelAttribute ImageRequestDTO imageDto) {

        log.info("Atualizando a boss de id: {}", bossId);
        service.update(bossId, bossDto, imageDto);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{bossId}")
    @Operation(summary = "Deletar.", description = "Deletar uma boss dentro do sistema.")
    public ResponseEntity<Void> deleteBoss(@PathVariable("bossId") UUID bossId) {

        log.info("Deletando a boss de id: {}", bossId.toString());

        service.delete(bossId);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{bossId}")
    @Operation(summary = "Encontrar.", description = "Pesquisar por uma boss dentro do sistema.")
    public ResponseEntity<BossResponseDTO> getBossById(@PathVariable("bossId") UUID bossId) {

        return ResponseEntity.ok(service.getById(bossId));

    }

    @GetMapping
    @Operation(summary = "Listar todas bosses.", description = "Mostra todas os bosses salvas no sistema.")
    public ResponseEntity<Set<BossResponseDTO>> getAllBosses() {

        return ResponseEntity.ok(service.getAll());

    }


}
