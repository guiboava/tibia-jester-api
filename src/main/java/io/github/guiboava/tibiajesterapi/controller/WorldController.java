package io.github.guiboava.tibiajesterapi.controller;

import io.github.guiboava.tibiajesterapi.controller.dto.request.WorldRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.WorldResponseDTO;
import io.github.guiboava.tibiajesterapi.service.WorldService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;
import java.util.UUID;

@RestController()
@RequestMapping("/worlds")
@RequiredArgsConstructor
@Tag(name = "Mundos")
@Slf4j
public class WorldController implements GenericController {


    private final WorldService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Salvar.", description = "Criar um novo world dentro do sistema.")
    public ResponseEntity<Void> createWorld(@RequestBody @Valid WorldRequestDTO dto) {

        log.info("Cadastrando um novo mundo: {}", dto.name());

        URI uri = generateHeaderLocation(service.save(dto));
        return ResponseEntity.created(uri).build();

    }

    @PutMapping("/{worldId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Atualizar.", description = "Atualizar um mundo dentro do sistema.")
    public ResponseEntity<Void> updateWorld(@PathVariable("worldId") UUID worldId, @RequestBody @Valid WorldRequestDTO dto) {

        log.info("Atualizando o mundo: {}", dto.name());
        service.update(worldId, dto);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{worldId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Atualizar.", description = "Atualizar um mundo dentro do sistema.")
    public ResponseEntity<Void> deleteWorld(@PathVariable("worldId") UUID worldId) {

        log.info("Deletando usuário de id: {}", worldId.toString());

        service.delete(worldId);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{worldId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER','VIP')")
    @Operation(summary = "Encontrar.", description = "Pesquisar por uma mundo dentro do sistema.")
    public ResponseEntity<WorldResponseDTO> getWorldById(@PathVariable("worldId") UUID worldId) {

        return ResponseEntity.ok(service.getById(worldId));

    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER','VIP')")
    @Operation(summary = "Listar mundos.", description = "Pesquisar por uma mundo dentro do sistema.")
    public ResponseEntity<Set<WorldResponseDTO>> getAllWorlds() {

        return ResponseEntity.ok(service.getAll());

    }

}
