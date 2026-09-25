package io.github.guiboava.tibiajesterapi.service;

import io.github.guiboava.tibiajesterapi.controller.dto.request.AccountInformationRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.AccountInformationResponseDTO;
import io.github.guiboava.tibiajesterapi.controller.mappers.AccountInformationMapper;
import io.github.guiboava.tibiajesterapi.entity.model.AccountInformation;
import io.github.guiboava.tibiajesterapi.exception.ResourceNotFoundException;
import io.github.guiboava.tibiajesterapi.repository.AccountInformationRepository;
import io.github.guiboava.tibiajesterapi.validator.AccountInformationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountInformationService {

    private final AccountInformationRepository accountInformationRepository;
    private final AccountInformationMapper accountInformationMapper;
    private final AccountInformationValidator accountInformationValidator;

    public UUID save(AccountInformationRequestDTO dto) {

        AccountInformation accountInformation = accountInformationMapper.toEntity(dto);

        accountInformationValidator.validate(accountInformation);
        return accountInformationRepository.save(accountInformation).getId();

    }

    public void update(UUID accountInformationId,
                       AccountInformationRequestDTO dto) {

        AccountInformation accountInformation = getEntityById(accountInformationId);
        accountInformationMapper.updateEntityFromDto(dto, accountInformation);
        accountInformationValidator.validate(accountInformation);
        accountInformationRepository.save(accountInformation);

    }

    public void delete(UUID accountInformationId) {

        AccountInformation accountInformation = getEntityById(accountInformationId);
        accountInformationRepository.delete(accountInformation);

    }

    public AccountInformationResponseDTO getById(UUID accountInformationId) {

        return accountInformationMapper.toDTO(getEntityById(accountInformationId));

    }

    public Set<AccountInformationResponseDTO> getAll() {

        return accountInformationRepository.findAll()
                .stream()
                .map(accountInformationMapper::toDTO)
                .collect(Collectors.toSet());

    }

    protected AccountInformation getEntityById(UUID accountInformationId) {

        return accountInformationRepository.findById(accountInformationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Informação de conta não encontrado para o id " + accountInformationId
                        )
                );

    }

}
