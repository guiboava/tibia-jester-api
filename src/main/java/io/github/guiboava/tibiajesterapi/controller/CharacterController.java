package io.github.guiboava.tibiajesterapi.controller;

import io.github.guiboava.tibiajesterapi.controller.dto.request.CharacterRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.CharacterResponseDTO;
import io.github.guiboava.tibiajesterapi.service.CharacterService;
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
@RequestMapping("/characters")
@RequiredArgsConstructor
@Tag(name = "Personagens")
@Slf4j
public class CharacterController implements GenericController {


    private final CharacterService service;

    @PostMapping
    @Operation(summary = "Salvar.", description = "Criar um novo character dentro do sistema.")
    public ResponseEntity<Void> createCharacter(@RequestBody @Valid CharacterRequestDTO dto) {

        log.info("Cadastrando um novo personagem: {}", dto.name());

        URI uri = generateHeaderLocation(service.save(dto));
        return ResponseEntity.created(uri).build();

    }

    @PutMapping("/{characterId}")
    @Operation(summary = "Atualizar.", description = "Atualizar um personagem dentro do sistema.")
    public ResponseEntity<Void> updateCharacter(@PathVariable("characterId") UUID characterId, @RequestBody @Valid CharacterRequestDTO dto) {

        log.info("Atualizando o personagem: {}", dto.name());
        service.update(characterId, dto);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{characterId}")
    @Operation(summary = "Atualizar.", description = "Atualizar um personagem dentro do sistema.")
    public ResponseEntity<Void> deleteCharacter(@PathVariable("characterId") UUID characterId) {

        log.info("Deletando usuário de id: {}", characterId.toString());

        service.delete(characterId);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{characterId}")
    @Operation(summary = "Encontrar.", description = "Pesquisar por uma personagem dentro do sistema.")
    public ResponseEntity<CharacterResponseDTO> getCharacterById(@PathVariable("characterId") UUID characterId) {

        return ResponseEntity.ok(service.getById(characterId));

    }

    @GetMapping
    @Operation(summary = "Listar personagems.", description = "Pesquisar por uma personagem dentro do sistema.")
    public ResponseEntity<Set<CharacterResponseDTO>> getAllCharacters() {

        return ResponseEntity.ok(service.getAll());

    }

}
