package io.github.guiboava.tibiajesterapi.controller;

import io.github.guiboava.tibiajesterapi.controller.dto.LatestNewRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.LatestNewResponseDTO;
import io.github.guiboava.tibiajesterapi.service.LatestNewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;
import java.util.UUID;

@RestController()
@RequestMapping("/latestNews")
@RequiredArgsConstructor
@Tag(name = "Novidades")
@Slf4j
public class LatestNewController implements GenericController {


    private final LatestNewService service;

    @PostMapping
    @Operation(summary = "Salvar.", description = "Criar um novo latestNew dentro do sistema.")
    public ResponseEntity<Void> createLatestNew(@RequestBody @Valid LatestNewRequestDTO dto) {

        log.info("Cadastrando um novo Novidade: {}", dto.newTitle());

        URI uri = generateHeaderLocation(service.save(dto));
        return ResponseEntity.created(uri).build();

    }

    @PutMapping("/{latestNewId}")
    @Operation(summary = "Atualizar.", description = "Atualizar um Novidade dentro do sistema.")
    public ResponseEntity<Void> updateLatestNew(@PathVariable("latestNewId") UUID latestNewId, @RequestBody @Valid LatestNewRequestDTO dto) {

        log.info("Atualizando o Novidade: {}", dto.newTitle());
        service.update(latestNewId, dto);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{latestNewId}")
    @Operation(summary = "Atualizar.", description = "Atualizar um Novidade dentro do sistema.")
    public ResponseEntity<Void> deleteLatestNew(@PathVariable("latestNewId") UUID latestNewId) {

        log.info("Deletando usuário de id: {}", latestNewId.toString());

        service.delete(latestNewId);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{latestNewId}")
    @Operation(summary = "Encontrar.", description = "Pesquisar por uma Novidade dentro do sistema.")
    public ResponseEntity<LatestNewResponseDTO> getLatestNewById(@PathVariable("latestNewId") UUID latestNewId) {

        return ResponseEntity.ok(service.getById(latestNewId));

    }

    @GetMapping
    @Operation(summary = "Listar Novidades.", description = "Pesquisar por uma Novidade dentro do sistema.")
    public ResponseEntity<Set<LatestNewResponseDTO>> getAllLatestNews() {

        return ResponseEntity.ok(service.getAll());

    }

}
