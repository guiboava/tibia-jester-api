package io.github.guiboava.tibiajesterapi.controller;

import io.github.guiboava.tibiajesterapi.controller.dto.request.AccountInformationRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.AccountInformationResponseDTO;
import io.github.guiboava.tibiajesterapi.service.AccountInformationService;
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
@RequestMapping("/accountInformations")
@RequiredArgsConstructor
@Tag(name = "AccountInformations")
@Slf4j
public class AccountInformationController implements GenericController {


    private final AccountInformationService service;

    @PostMapping
    @Operation(summary = "Salvar.", description = "Criar uma nova AccountInformation dentro do sistema.")
    public ResponseEntity<Void> createAccountInformation(@RequestBody @Valid AccountInformationRequestDTO dto) {

        log.info("Cadastrando uma nova informação de conta");

        URI uri = generateHeaderLocation(service.save(dto));
        return ResponseEntity.created(uri).build();

    }

    @PutMapping("/{accountInformationId}")
    @Operation(summary = "Atualizar.", description = "Atualizar uma AccountInformation dentro do sistema.")
    public ResponseEntity<Void> updateAccountInformation(@PathVariable("accountInformationId") UUID accountInformationId,
                                                         @RequestBody @Valid AccountInformationRequestDTO dto) {

        log.info("Atualizando uma Informação de conta");

        service.update(accountInformationId, dto);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{accountInformationId}")
    @Operation(summary = "Atualizar.", description = "Atualizar um personagem dentro do sistema.")
    public ResponseEntity<Void> deleteAccountInformation(@PathVariable("accountInformationId") UUID accountInformationId) {

        log.info("Deletando a accountInformationa de id: {}", accountInformationId);

        service.delete(accountInformationId);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{accountInformationId}")
    @Operation(summary = "Encontrar.", description = "Pesquisar por uma accountInformationa dentro do sistema.")
    public ResponseEntity<AccountInformationResponseDTO> getAccountInformationById(@PathVariable("accountInformationId") UUID accountInformationId) {

        return ResponseEntity.ok(service.getById(accountInformationId));

    }

    @GetMapping
    @Operation(summary = "Listar personagems.", description = "Pesquisar por uma personagem dentro do sistema.")
    public ResponseEntity<Set<AccountInformationResponseDTO>> getAllAccountInformations() {

        return ResponseEntity.ok(service.getAll());

    }

}
