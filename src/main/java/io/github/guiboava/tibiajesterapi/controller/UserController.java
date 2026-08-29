package io.github.guiboava.tibiajesterapi.controller;

import io.github.guiboava.tibiajesterapi.controller.dto.UserRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.UserResponseDTO;
import io.github.guiboava.tibiajesterapi.entity.enums.UserType;
import io.github.guiboava.tibiajesterapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Usuário")
@Slf4j
public class UserController implements GenericController {

    private final UserService service;

    @PostMapping
    @Operation(summary = "Salvar.", description = "Criar um novo usuário dentro do sistema.")
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserRequestDTO dto) {

        log.info("Cadastrando um novo usuário: {}", dto.name());

        URI uri = generateHeaderLocation(service.save(dto));
        return ResponseEntity.created(uri).build();

    }

    @PutMapping("/{userId}")
    @Operation(summary = "Atualizar.", description = "Atualizar um usuário dentro do sistema.")
    public ResponseEntity<Void> updateUser(@PathVariable("userId") UUID userId,@RequestBody @Valid UserRequestDTO dto) {

        log.info("Atualizando o usuário: {}", dto.name());
        service.update(userId,dto);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Deletar.", description = "Deletar um usuário dentro do sistema.")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") UUID userId) {

        log.info("Deletando usuário de id: {}", userId.toString());

        service.delete(userId);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{userId}")
    @Operation(summary = "Encontrar.", description = "Pesquisar por um usuário dentro do sistema.")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("userId") UUID userId) {

        return ResponseEntity.ok(service.getById(userId));

    }

    @GetMapping
    @Operation(summary = "Pesquisar.", description = "Pesquisar por um usuário dentro do sistema.")
    public ResponseEntity<List<UserResponseDTO>> searchUser(@RequestParam(value = "name", required = false) String name,
                                                            @RequestParam(value = "login", required = false) String login,
                                                            @RequestParam(value = "email", required = false) String email,
                                                            @RequestParam(value = "user_type", required = false) UserType userType
    ) {
        return ResponseEntity.ok(service.searchByExample(name, login, email, userType));
    }

}
