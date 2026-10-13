package io.github.guiboava.tibiajesterapi.controller;

import io.github.guiboava.tibiajesterapi.controller.dto.request.AchievementRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.AchievementResponseDTO;
import io.github.guiboava.tibiajesterapi.service.AchievementService;
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
@RequestMapping("/achievements")
@RequiredArgsConstructor
@Tag(name = "Achievement")
@Slf4j
public class AchievementController implements GenericController {

    private final AchievementService serivce;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Salvar.", description = "Criar um novo Achievement dentro do sistema.")
    public ResponseEntity<Void> createAchievement(@RequestBody @Valid AchievementRequestDTO dto) {

        log.info("Cadastrando um novo Achievement: {}", dto.name());

        URI uri = generateHeaderLocation(serivce.save(dto));
        return ResponseEntity.created(uri).build();

    }

    @PutMapping("/{achievementId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Atualizar.", description = "Atualizar uma Achievement dentro do sistema.")
    public ResponseEntity<Void> updateAchievement(@PathVariable("achievementId") UUID achievementId, @RequestBody @Valid AchievementRequestDTO dto) {

        log.info("Atualizando a Achievement: {}", dto.name());

        serivce.update(achievementId, dto);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{achievementId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Atualizar.", description = "Atualizar um Achievement dentro do sistema.")
    public ResponseEntity<Void> deleteAchievement(@PathVariable("achievementId") UUID achievementId) {

        log.info("Deletando a Achievement de id: {}", achievementId);

        serivce.delete(achievementId);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{achievementId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER','VIP')")
    @Operation(summary = "Encontrar.", description = "Pesquisar por uma Achievement dentro do sistema.")
    public ResponseEntity<AchievementResponseDTO> getAchievementById(@PathVariable("achievementId") UUID achievementId) {

        return ResponseEntity.ok(serivce.getById(achievementId));

    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER','VIP')")
    @Operation(summary = "Listar Achievement.", description = "Pesquisar por um Achievement dentro do sistema.")
    public ResponseEntity<Set<AchievementResponseDTO>> getAllAchievements() {

        return ResponseEntity.ok(serivce.getAll());

    }

}
