package io.github.guiboava.tibiajesterapi.service;

import io.github.guiboava.tibiajesterapi.controller.dto.HuntStatusRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.HuntStatusResponseDTO;
import io.github.guiboava.tibiajesterapi.controller.mappers.HuntStatusMapper;
import io.github.guiboava.tibiajesterapi.entity.model.Hunt;
import io.github.guiboava.tibiajesterapi.entity.model.HuntStatus;
import io.github.guiboava.tibiajesterapi.exception.ResourceNotFoundException;
import io.github.guiboava.tibiajesterapi.repository.HuntStatusRepository;
import io.github.guiboava.tibiajesterapi.validator.HuntStatusValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HuntStatusService {

    private final HuntStatusMapper huntStatusMapper;
    private final HuntStatusValidator huntStatusValidator;
    private final HuntStatusRepository huntStatusRepository;

    private final HuntService huntService;

    public UUID save(UUID huntId, HuntStatusRequestDTO dto) {

        Hunt hunt = huntService.getEntityById(huntId);
        HuntStatus huntStatus = huntStatusMapper.toEntity(dto);

        huntStatus.setHunt(hunt);

        huntStatusValidator.validate(huntStatus, huntId);
        return huntStatusRepository.save(huntStatus).getId();

    }

    public void update(UUID huntId, UUID huntStatusId, HuntStatusRequestDTO dto) {

        huntService.getEntityById(huntId);
        HuntStatus huntStatus = getEntityById(huntStatusId);

        huntStatusMapper.updateEntityFromDto(dto, huntStatus);

        huntStatusValidator.validate(huntStatus, huntId);
        huntStatusRepository.save(huntStatus);

    }

    public void delete(UUID huntId, UUID huntStatusId) {

        huntService.getEntityById(huntId);
        HuntStatus huntStatus = getEntityById(huntStatusId);

        huntStatusValidator.validate(huntStatus, huntId);
        huntStatusRepository.delete(huntStatus);

    }

    public HuntStatusResponseDTO getById(UUID huntId, UUID huntStatusId) {

        huntService.getEntityById(huntId);
        HuntStatus huntStatus = getEntityById(huntStatusId);
        huntStatusValidator.validate(huntStatus, huntId);

        return huntStatusMapper.toDTO(huntStatus);
    }

    public Set<HuntStatusResponseDTO> getAll(UUID huntId) {

        Hunt hunt = huntService.getEntityById(huntId);
        return huntStatusRepository.findAllByHunt(hunt).stream().map(huntStatusMapper::toDTO).collect(Collectors.toSet());

    }

    private HuntStatus getEntityById(UUID huntStatusId) {
        return huntStatusRepository.findById(huntStatusId).orElseThrow(() -> new ResourceNotFoundException("Status de hunt não encontrada."));
    }

}
