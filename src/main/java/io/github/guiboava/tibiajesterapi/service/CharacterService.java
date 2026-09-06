package io.github.guiboava.tibiajesterapi.service;

import io.github.guiboava.tibiajesterapi.controller.dto.request.CharacterRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.CharacterResponseDTO;
import io.github.guiboava.tibiajesterapi.controller.mappers.CharacterMapper;
import io.github.guiboava.tibiajesterapi.entity.model.Character;
import io.github.guiboava.tibiajesterapi.entity.model.World;
import io.github.guiboava.tibiajesterapi.exception.ResourceNotFoundException;
import io.github.guiboava.tibiajesterapi.repository.CharacterRepository;
import io.github.guiboava.tibiajesterapi.validator.CharacterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterMapper characterMapper;
    private final CharacterValidator characterValidator;
    private final CharacterRepository characterRepository;

    private final WorldService worldService;

    public UUID save(CharacterRequestDTO dto) {

        World world = worldService.getEntityById(dto.worldId());

        Character character = characterMapper.toEntity(dto);

        character.setWorld(world);

        characterValidator.validate(character);
        return characterRepository.save(character).getId();

    }

    public void update(UUID characterId, CharacterRequestDTO dto) {

        World world = worldService.getEntityById(dto.worldId());

        Character character = getEntityById(characterId);
        character.setWorld(world);
        characterMapper.updateEntityFromDto(dto, character);

        characterValidator.validate(character);
        characterRepository.save(character);

    }

    public void delete(UUID characterId) {

        Character character = getEntityById(characterId);

        characterRepository.delete(character);

    }

    public CharacterResponseDTO getById(UUID characterId) {

        return characterRepository.findById(characterId).map(characterMapper::toDTO).orElseThrow(() -> new ResourceNotFoundException("Não foi encrontrado nenhum dado de mundo"));

    }

    public Set<CharacterResponseDTO> getAll() {

        return characterRepository.findAll().stream().map(characterMapper::toDTO).collect(Collectors.toSet());


    }

    private Character getEntityById(UUID characterId) {
        return characterRepository.findById(characterId).orElseThrow(() -> new ResourceNotFoundException("Mundo não encontrado para o id " + characterId));
    }

}
