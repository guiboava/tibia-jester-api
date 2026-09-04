package io.github.guiboava.tibiajesterapi.service;

import io.github.guiboava.tibiajesterapi.controller.dto.WorldRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.WorldResponseDTO;
import io.github.guiboava.tibiajesterapi.controller.mappers.WorldMapper;
import io.github.guiboava.tibiajesterapi.entity.model.World;
import io.github.guiboava.tibiajesterapi.exception.ResourceNotFoundException;
import io.github.guiboava.tibiajesterapi.repository.WorldRepository;
import io.github.guiboava.tibiajesterapi.validator.WorldValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorldService {

    private final WorldMapper worldMapper;
    private final WorldValidator worldValidator;
    private final WorldRepository worldRepository;

    public UUID save(WorldRequestDTO dto) {

        World world = worldMapper.toEntity(dto);

        worldValidator.validate(world);
        return worldRepository.save(world).getId();

    }

    public void update(UUID worldId, WorldRequestDTO dto) {

        World world = worldRepository.findById(worldId).orElseThrow(() -> new ResourceNotFoundException("Mundo não encontrado para o id " + worldId));
        worldMapper.updateEntityFromDto(dto, world);

        worldValidator.validate(world);
        worldRepository.save(world);

    }

    public void delete(UUID worldId) {

        World world = worldRepository.findById(worldId).orElseThrow(() -> new EntityNotFoundException("Mundo não encontrado."));

        worldRepository.delete(world);

    }

    public WorldResponseDTO getById(UUID worldId) {

        return worldRepository.findById(worldId).map(worldMapper::toDTO).orElseThrow(() -> new ResourceNotFoundException("Não foi encrontrado nenhum dado de mundo"));

    }

    public Set<WorldResponseDTO> getAll() {

        return worldRepository.findAll().stream().map(worldMapper::toDTO).collect(Collectors.toSet());


    }
}
