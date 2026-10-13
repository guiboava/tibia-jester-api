package io.github.guiboava.tibiajesterapi.service;

import io.github.guiboava.tibiajesterapi.controller.dto.request.UserCharacterRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.UserCharacterResponseDTO;
import io.github.guiboava.tibiajesterapi.controller.mappers.UserCharacterMapper;
import io.github.guiboava.tibiajesterapi.entity.model.Character;
import io.github.guiboava.tibiajesterapi.entity.model.User;
import io.github.guiboava.tibiajesterapi.entity.model.UserCharacter;
import io.github.guiboava.tibiajesterapi.exception.ResourceNotFoundException;
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

    @Transactional
    public UUID linkUserCharacter(User user,
                                  UUID characterId,
                                  UserCharacterRequestDTO dto) {



        Character character = characterService.getEntityById(characterId);

        UserCharacter userCharacter = userCharacterMapper.toEntity(dto);
        userCharacter.setCharacter(character);
        userCharacter.setUser(user);
        userCharacterValidator.validate(userCharacter);



        return userCharacterRepository.save(userCharacter).getId();

    }

    @Transactional
    public void updateMain(User user,
                           UUID characterId,
                           UserCharacterRequestDTO dto) {

        UserCharacter userCharacter = getEntityById(user, characterId);

        userCharacterMapper.updateEntityFromDto(dto, userCharacter);
        userCharacterValidator.validate(userCharacter);
        userCharacterRepository.save(userCharacter);

    }

    @Transactional
    public void deleteLink(User user,
                           UUID characterId) {

        UserCharacter userCharacter = getEntityById(user, characterId);

        userCharacterRepository.delete(userCharacter);

    }

    @Transactional(readOnly = true)
    public UserCharacterResponseDTO getById(User user,
                                            UUID characterId) {

        return userCharacterMapper.toDTO(getEntityById(user, characterId));

    }

    @Transactional(readOnly = true)
    public Set<UserCharacterResponseDTO> getAll(User user) {

        return userCharacterRepository
                .findAllByUser(user)
                .map(userCharacter -> {
                    return userCharacter.stream().map(userCharacterMapper::toDTO).collect(Collectors.toSet());
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Vínculo não encontrado"
                        )
                );

    }

    protected UserCharacter getEntityById(User user,
                                          UUID characterId) {

        return userCharacterRepository.findByUserAndCharacter(user, characterService.getEntityById(characterId))
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Vínculo não encontrado"
                        )
                );

    }

}
