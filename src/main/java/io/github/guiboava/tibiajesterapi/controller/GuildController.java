package io.github.guiboava.tibiajesterapi.controller;

import io.github.guiboava.tibiajesterapi.controller.dto.request.GuildRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.GuildResponseDTO;
import io.github.guiboava.tibiajesterapi.service.GuildService;
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
@RequestMapping("/guilds")
@RequiredArgsConstructor
@Tag(name = "Guilds")
@Slf4j
public class GuildController implements GenericController {


    private final GuildService service;

    @PostMapping
    @Operation(summary = "Salvar.", description = "Criar uma nova Guild dentro do sistema.")
    public ResponseEntity<Void> createGuild(@RequestBody @Valid GuildRequestDTO dto) {

        log.info("Cadastrando um novo personagem: {}", dto.name());

        URI uri = generateHeaderLocation(service.save(dto));
        return ResponseEntity.created(uri).build();

    }

    @PutMapping("/{guildId}")
    @Operation(summary = "Atualizar.", description = "Atualizar uma Guild dentro do sistema.")
    public ResponseEntity<Void> updateGuild(@PathVariable("guildId") UUID guildId, @RequestBody @Valid GuildRequestDTO dto) {

        log.info("Atualizando a guild: {}", dto.name());

        service.update(guildId, dto);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{guildId}")
    @Operation(summary = "Atualizar.", description = "Atualizar um personagem dentro do sistema.")
    public ResponseEntity<Void> deleteGuild(@PathVariable("guildId") UUID guildId) {

        log.info("Deletando a guilda de id: {}", guildId);

        service.delete(guildId);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{guildId}")
    @Operation(summary = "Encontrar.", description = "Pesquisar por uma guilda dentro do sistema.")
    public ResponseEntity<GuildResponseDTO> getGuildById(@PathVariable("guildId") UUID guildId) {

        return ResponseEntity.ok(service.getById(guildId));

    }

    @GetMapping
    @Operation(summary = "Listar personagems.", description = "Pesquisar por uma personagem dentro do sistema.")
    public ResponseEntity<Set<GuildResponseDTO>> getAllGuilds() {

        return ResponseEntity.ok(service.getAll());

    }

}
