package io.github.guiboava.tibiajesterapi.controller;

import io.github.guiboava.tibiajesterapi.controller.dto.request.ClientRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.ClientResponseDTO;
import io.github.guiboava.tibiajesterapi.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Tag(name = "Clientes API")
@Slf4j
public class ClientController {

    private final ClientService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Cadastro de client", description = "Cadastra as credenciais de um client parceiro.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso."),
            @ApiResponse(responseCode = "401", description = "Inautorizado."),
            @ApiResponse(responseCode = "422", description = "Erro de validação.")
    })
    public ClientResponseDTO save(@RequestBody @Valid ClientRequestDTO dto) {
        log.info("Criando credenciais de client: {}", dto.name());
        return service.save(dto);
    }
}