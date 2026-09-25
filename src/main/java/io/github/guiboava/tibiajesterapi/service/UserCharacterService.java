package io.github.guiboava.tibiajesterapi.service;

import io.github.guiboava.tibiajesterapi.controller.dto.request.UserCharacterRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.UserCharacterResponseDTO;
import io.github.guiboava.tibiajesterapi.controller.mappers.UserCharacterMapper;
import io.github.guiboava.tibiajesterapi.entity.model.Character;
import io.github.guiboava.tibiajesterapi.entity.model.User;
import io.github.guiboava.tibiajesterapi.entity.model.UserCharacter;
import io.github.guiboava.tibiajesterapi.exception.EntityNotFoundException;
import io.github.guiboava.tibiajesterapi.repository.UserCharacterRepository;
import io.github.guiboava.tibiajesterapi.validator.UserCharacterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCharacterService {

    private final UserCharacterRepository userCharacterRepository;
    private final UserCharacterMapper userCharacterMapper;
    private final UserCharacterValidator userCharacterValidator;

    private final UserService userService;
    private final CharacterService characterService;

    @Transactional(readOnly = true)
    public UUID linkUserCharacter(UUID userId,
                                  UUID characterId,
                                  UserCharacterRequestDTO dto) {

        User user = userService.getEntityById(userId);
        Character character = characterService.getEntityById(characterId);

        UserCharacter userCharacter = userCharacterMapper.toEntity(dto);
        userCharacter.setCharacter(character);
        userCharacter.setUser(user);
        userCharacterValidator.validate(userCharacter);

        return userCharacterRepository.save(userCharacter).getId();

    }

    @Transactional
    public void updateMain(UUID userId,
                           UUID characterId,
                           UserCharacterRequestDTO dto) {

        UserCharacter userCharacter = getEntityById(userId, characterId);

        userCharacterMapper.updateEntityFromDto(dto, userCharacter);
        userCharacterValidator.validate(userCharacter);
        userCharacterRepository.save(userCharacter);

    }

    @Transactional
    public void deleteLink(UUID userId,
                           UUID characterId) {

        UserCharacter userCharacter = getEntityById(userId, characterId);

        userCharacterRepository.delete(userCharacter);

    }

    @Transactional(readOnly = true)
    public UserCharacterResponseDTO getById(UUID userId,
                                            UUID characterId) {

        return userCharacterMapper.toDTO(getEntityById(userId, characterId));

    }

    @Transactional(readOnly = true)
    public Set<UserCharacterResponseDTO> getAll(UUID userId) {

        return userCharacterRepository
                .findAllByUser(userService.getEntityById(userId))
                .map(userCharacter -> {
                    return userCharacter.stream().map(userCharacterMapper::toDTO).collect(Collectors.toSet());
                })
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Vínculo não encontrado"
                        )
                );

    }

    protected UserCharacter getEntityById(UUID userId,
                                          UUID characterId) {

        return userCharacterRepository.findByUserAndCharacter(userService.getEntityById(userId), characterService.getEntityById(characterId))
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Vínculo não encontrado"
                        )
                );

    }

}
