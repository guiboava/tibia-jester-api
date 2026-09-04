package io.github.guiboava.tibiajesterapi.service;

import io.github.guiboava.tibiajesterapi.controller.dto.LatestNewRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.LatestNewResponseDTO;
import io.github.guiboava.tibiajesterapi.controller.mappers.LatestNewMapper;
import io.github.guiboava.tibiajesterapi.entity.model.LatestNew;
import io.github.guiboava.tibiajesterapi.exception.ResourceNotFoundException;
import io.github.guiboava.tibiajesterapi.repository.LatestNewRepository;
import io.github.guiboava.tibiajesterapi.validator.LatestNewValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LatestNewService {

    private final LatestNewMapper latestNewMapper;
    private final LatestNewValidator latestNewValidator;
    private final LatestNewRepository latestNewRepository;

    public UUID save(LatestNewRequestDTO dto) {

        LatestNew latestNew = latestNewMapper.toEntity(dto);

        latestNewValidator.validate(latestNew);
        return latestNewRepository.save(latestNew).getId();

    }

    public void update(UUID latestNewId, LatestNewRequestDTO dto) {

        LatestNew latestNew = latestNewRepository.findById(latestNewId).orElseThrow(() -> new ResourceNotFoundException("Novidade não encontrado para o id " + latestNewId));
        latestNewMapper.updateEntityFromDto(dto, latestNew);

        latestNewValidator.validate(latestNew);
        latestNewRepository.save(latestNew);

    }

    public void delete(UUID latestNewId) {

        LatestNew latestNew = latestNewRepository.findById(latestNewId).orElseThrow(() -> new EntityNotFoundException("Novidade não encontrado."));

        latestNewRepository.delete(latestNew);

    }

    public LatestNewResponseDTO getById(UUID latestNewId) {

        return latestNewRepository.findById(latestNewId).map(latestNewMapper::toDTO).orElseThrow(() -> new ResourceNotFoundException("Não foi encrontrado nenhum dado de novidade"));

    }

    public Set<LatestNewResponseDTO> getAll() {

        return latestNewRepository.findAll().stream().map(latestNewMapper::toDTO).collect(Collectors.toSet());


    }
}
