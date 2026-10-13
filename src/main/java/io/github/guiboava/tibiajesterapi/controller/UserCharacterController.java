package io.github.guiboava.tibiajesterapi.controller;

import io.github.guiboava.tibiajesterapi.controller.dto.request.UserCharacterRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.UserCharacterResponseDTO;
import io.github.guiboava.tibiajesterapi.entity.model.User;
import io.github.guiboava.tibiajesterapi.service.UserCharacterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;
import java.util.UUID;

@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "UserCharacters")
@Slf4j
public class UserCharacterController implements GenericController {


    private final UserCharacterService service;

    @PostMapping("/characters/{characterId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER','VIP')")
    @Operation(summary = "Vincular.", description = "Vincula um personagem a um usuario do sistema.")
    public ResponseEntity<Void> createLink(@AuthenticationPrincipal User user,
                                           @PathVariable("characterId") UUID characterId,
                                           @RequestBody @Valid UserCharacterRequestDTO dto) {

        log.info("Vinculando um novo personagem ao usuario: {}", user.getId());

        URI uri = generateHeaderLocation(service.linkUserCharacter(user, characterId, dto));

        return ResponseEntity.created(uri).build();

    }

    @PatchMapping("/characters/{characterId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER','VIP')")
    @Operation(summary = "Modificar Main.", description = "Atualizar uma UserCharacters como main dentro do sistema.")
    public ResponseEntity<Void> updateUserCharacters(@AuthenticationPrincipal User user,
                                                     @PathVariable("characterId") UUID characterId,
                                                     @RequestBody @Valid UserCharacterRequestDTO dto) {

        log.info("Modificando personagem main do usuario: {}", user.getId());

        service.updateMain(user, characterId, dto);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/characters/{characterId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER','VIP')")
    @Operation(summary = "Desvincular.", description = "Deleta um personagem da lista de usuario do sistema.")
    public ResponseEntity<Void> deleteLinkFromUserCharacters(@AuthenticationPrincipal User user,
                                                             @PathVariable("characterId") UUID characterId) {

        log.info("Deletando userCharactersa de id: {}", characterId);

        service.deleteLink(user, characterId);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/characters/{userCharactersId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER','VIP')")
    @Operation(summary = "Encontrar.", description = "Pesquisar por uma userCharactersa dentro do sistema.")
    public ResponseEntity<UserCharacterResponseDTO> getUserCharactersById(@AuthenticationPrincipal User user,
                                                                          @PathVariable("userCharactersId") UUID userCharactersId) {

        return ResponseEntity.ok(service.getById(user, userCharactersId));

    }

    @GetMapping("/characters")
    @PreAuthorize("hasAnyRole('ADMIN','USER','VIP')")
    @Operation(summary = "Listar personagems.", description = "Pesquisar por uma personagem dentro do sistema.")
    public ResponseEntity<Set<UserCharacterResponseDTO>> getAllUserCharacterss(@AuthenticationPrincipal User user) {

        return ResponseEntity.ok(service.getAll(user));

    }

}
