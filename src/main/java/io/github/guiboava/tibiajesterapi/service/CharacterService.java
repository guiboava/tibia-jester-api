package io.github.guiboava.tibiajesterapi.service;

import io.github.guiboava.tibiajesterapi.controller.dto.request.CharacterRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.CharacterResponseDTO;
import io.github.guiboava.tibiajesterapi.controller.mappers.CharacterMapper;
import io.github.guiboava.tibiajesterapi.entity.model.Achievement;
import io.github.guiboava.tibiajesterapi.entity.model.Character;
import io.github.guiboava.tibiajesterapi.entity.model.Guild;
import io.github.guiboava.tibiajesterapi.entity.model.World;
import io.github.guiboava.tibiajesterapi.exception.ResourceNotFoundException;
import io.github.guiboava.tibiajesterapi.repository.CharacterRepository;
import io.github.guiboava.tibiajesterapi.validator.CharacterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final AchievementService achievementService;
    private final GuildService guildService;

    @Transactional
    public UUID save(CharacterRequestDTO dto) {

        Character character = characterMapper.toEntity(dto);
        applyRelations(character, dto);

        characterValidator.validate(character);
        return characterRepository.save(character).getId();
    }

    @Transactional
    public void update(UUID characterId, CharacterRequestDTO dto) {

        Character character = getEntityById(characterId);
        applyRelations(character, dto);
        characterMapper.updateEntityFromDto(dto, character);

        characterValidator.validate(character);
        characterRepository.save(character);
    }


    @Transactional
    public void delete(UUID characterId) {
        Character character = getEntityById(characterId);
        characterRepository.delete(character);
    }

    public CharacterResponseDTO getById(UUID characterId) {
        return characterMapper.toDTO(getEntityById(characterId));
    }

    public Set<CharacterResponseDTO> getAll() {
        return characterRepository.findAll().stream()
                .map(characterMapper::toDTO)
                .collect(Collectors.toSet());
    }

    private Character getEntityById(UUID characterId) {
        return characterRepository.findById(characterId)
                .orElseThrow(() -> new ResourceNotFoundException("Personagem não encontrado para o id " + characterId));
    }

    private void applyRelations(Character character, CharacterRequestDTO dto) {

        World world = dto.worldId() == null
                ? null
                : worldService.getEntityById(dto.worldId());

        Guild guild = dto.guildId() == null
                ? null
                : guildService.getEntityById(dto.guildId());

        character.setWorld(world);
        character.setGuild(guild);

        if (dto.achievementIds() != null) {
            Set<Achievement> achievements = dto.achievementIds().stream()
                    .map(achievementService::getEntityById)
                    .collect(Collectors.toSet());
            character.setAchievements(achievements);
        }
    }
}