package io.github.guiboava.tibiajesterapi.controller;

import io.github.guiboava.tibiajesterapi.controller.dto.request.UserCharacterRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.UserCharacterResponseDTO;
import io.github.guiboava.tibiajesterapi.service.UserCharacterService;
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
@RequestMapping("/users/{userId}")
@RequiredArgsConstructor
@Tag(name = "UserCharacters")
@Slf4j
public class UserCharacterController implements GenericController {


    private final UserCharacterService service;

    @PostMapping("/characters/{characterId}")
    @Operation(summary = "Vincular.", description = "Vincula um personagem a um usuario do sistema.")
    public ResponseEntity<Void> createLink(@PathVariable("userId") UUID userId,
                                           @PathVariable("characterId") UUID characterId,
                                           @RequestBody @Valid UserCharacterRequestDTO dto) {

        log.info("Vinculando um novo personagem ao usuario: {}", userId);

        URI uri = generateHeaderLocation(service.linkUserCharacter(userId, characterId, dto));

        return ResponseEntity.created(uri).build();

    }

    @PatchMapping("/characters/{characterId}")
    @Operation(summary = "Modificar Main.", description = "Atualizar uma UserCharacters como main dentro do sistema.")
    public ResponseEntity<Void> updateUserCharacters(@PathVariable("userId") UUID userId,
                                                     @PathVariable("characterId") UUID characterId,
                                                     @RequestBody @Valid UserCharacterRequestDTO dto) {

        log.info("Modificando personagem main do usuario: {}", userId);

        service.updateMain(userId, characterId, dto);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/characters/{characterId}")
    @Operation(summary = "Desvincular.", description = "Deleta um personagem da lista de usuario do sistema.")
    public ResponseEntity<Void> deleteLinkFromUserCharacters(@PathVariable("userId") UUID userId,
                                                             @PathVariable("characterId") UUID characterId) {

        log.info("Deletando userCharactersa de id: {}", characterId);

        service.deleteLink(userId, characterId);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/characters/{userCharactersId}")
    @Operation(summary = "Encontrar.", description = "Pesquisar por uma userCharactersa dentro do sistema.")
    public ResponseEntity<UserCharacterResponseDTO> getUserCharactersById(@PathVariable("userId") UUID userId,
                                                                          @PathVariable("userCharactersId") UUID userCharactersId) {

        return ResponseEntity.ok(service.getById(userId, userCharactersId));

    }

    @GetMapping("/characters")
    @Operation(summary = "Listar personagems.", description = "Pesquisar por uma personagem dentro do sistema.")
    public ResponseEntity<Set<UserCharacterResponseDTO>> getAllUserCharacterss(@PathVariable("userId") UUID userId) {

        return ResponseEntity.ok(service.getAll(userId));

    }

}
