package io.github.guiboava.tibiajesterapi.controller;

import io.github.guiboava.tibiajesterapi.controller.dto.HuntStatusRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.HuntStatusResponseDTO;
import io.github.guiboava.tibiajesterapi.service.HuntStatusService;
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
@RequestMapping("hunts/{huntId}/huntStatus")
@RequiredArgsConstructor
@Tag(name = "Hunt Status")
@Slf4j
public class HuntStatusController implements GenericController {


    private final HuntStatusService service;

    @PostMapping
    @Operation(summary = "Salvar.", description = "Criar um novo huntStatus dentro do sistema.")
    public ResponseEntity<Void> createHuntStatus(@PathVariable("huntId") UUID huntId,
                                                 @RequestBody @Valid HuntStatusRequestDTO dto) {

        log.info("Cadastrando um novo HuntStatus para a hunt: {}", huntId);

        URI uri = generateHeaderLocation(service.save(huntId, dto));
        return ResponseEntity.created(uri).build();

    }

    @PutMapping("/{huntStatusId}")
    @Operation(summary = "Atualizar.", description = "Atualizar um HuntStatus dentro do sistema.")
    public ResponseEntity<Void> updateHuntStatus(@PathVariable("huntId") UUID huntId,
                                                 @PathVariable("huntStatusId") UUID huntStatusId,
                                                 @RequestBody @Valid HuntStatusRequestDTO dto) {

        log.info("Atualizando o HuntStatus da hunt: {}", huntId);
        service.update(huntId, huntStatusId, dto);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{huntStatusId}")
    @Operation(summary = "Deletar.", description = "Atualizar um HuntStatus dentro do sistema.")
    public ResponseEntity<Void> deleteHuntStatus(@PathVariable("huntId") UUID huntId,
                                                 @PathVariable("huntStatusId") UUID huntStatusId) {

        log.info("Deletando Status de id: {}, da hunt: {}", huntStatusId.toString(), huntId);

        service.delete(huntId, huntStatusId);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{huntStatusId}")
    @Operation(summary = "Encontrar.", description = "Pesquisar por uma HuntStatus dentro do sistema.")
    public ResponseEntity<HuntStatusResponseDTO> getHuntStatusById(@PathVariable("huntId") UUID huntId,
                                                                   @PathVariable("huntStatusId") UUID huntStatusId) {

        return ResponseEntity.ok(service.getById(huntId, huntStatusId));

    }

    @GetMapping
    @Operation(summary = "Listar HuntStatus.", description = "Pesquisar por uma HuntStatus dentro do sistema.")
    public ResponseEntity<Set<HuntStatusResponseDTO>> getAllHuntStatus(@PathVariable("huntId") UUID huntId) {

        return ResponseEntity.ok(service.getAll(huntId));

    }

}
