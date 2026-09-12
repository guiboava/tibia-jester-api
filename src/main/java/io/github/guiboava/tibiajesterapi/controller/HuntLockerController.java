package io.github.guiboava.tibiajesterapi.controller;

import io.github.guiboava.tibiajesterapi.controller.dto.request.HuntLockerRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.HuntLockerResponseDTO;
import io.github.guiboava.tibiajesterapi.service.HuntLockerService;
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
@RequestMapping("/huntLockers")
@RequiredArgsConstructor
@Tag(name = "HuntLockers")
@Slf4j
public class HuntLockerController implements GenericController {


    private final HuntLockerService service;

    @PostMapping
    @Operation(summary = "Salvar.", description = "Criar uma nova HuntLocker dentro do sistema.")
    public ResponseEntity<Void> createHuntLocker(@RequestBody @Valid HuntLockerRequestDTO dto) {

        log.info("Cadastrando um novo personagem: {}", dto.huntId());

        URI uri = generateHeaderLocation(service.save(dto));
        return ResponseEntity.created(uri).build();

    }

    @PutMapping("/{huntLockerId}")
    @Operation(summary = "Atualizar.", description = "Atualizar uma HuntLocker dentro do sistema.")
    public ResponseEntity<Void> updateHuntLocker(@PathVariable("huntLockerId") UUID huntLockerId, @RequestBody @Valid HuntLockerRequestDTO dto) {

        log.info("Atualizando a huntLocker: {}", dto.huntId());

        service.update(huntLockerId, dto);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{huntLockerId}")
    @Operation(summary = "Atualizar.", description = "Atualizar um personagem dentro do sistema.")
    public ResponseEntity<Void> deleteHuntLocker(@PathVariable("huntLockerId") UUID huntLockerId) {

        log.info("Deletando a huntLockera de id: {}", huntLockerId);

        service.delete(huntLockerId);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{huntLockerId}")
    @Operation(summary = "Encontrar.", description = "Pesquisar por uma huntLockera dentro do sistema.")
    public ResponseEntity<HuntLockerResponseDTO> getHuntLockerById(@PathVariable("huntLockerId") UUID huntLockerId) {

        return ResponseEntity.ok(service.getById(huntLockerId));

    }

    @GetMapping
    @Operation(summary = "Listar personagems.", description = "Pesquisar por uma personagem dentro do sistema.")
    public ResponseEntity<Set<HuntLockerResponseDTO>> getAllHuntLockers() {

        return ResponseEntity.ok(service.getAll());

    }

}
